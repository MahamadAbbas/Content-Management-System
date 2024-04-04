package com.example.cms.serviceimpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.cms.dto.BlogPostRequest;
import com.example.cms.dto.BlogPostResponse;
import com.example.cms.dto.PublishRequest;
import com.example.cms.dto.PublishResponse;
import com.example.cms.enums.PostType;
import com.example.cms.exception.BlogPostNotFoundByIdException;
import com.example.cms.exception.IllegalAccessRequestException;
import com.example.cms.exception.UserNotFoundByIdException;
import com.example.cms.model.BlogPost;
import com.example.cms.model.Publish;
import com.example.cms.repository.BlogPostRepository;
import com.example.cms.repository.BlogRepository;
import com.example.cms.repository.ContributionPanelRepository;
import com.example.cms.repository.PublishRepository;
import com.example.cms.repository.UserRepository;
import com.example.cms.service.PublishService;
import com.example.cms.utility.ResponseStructure;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PublishServiceImpl implements PublishService {

	private BlogPostRepository blogPostRepository;
	private ResponseStructure<PublishResponse> structure;
	private PublishRepository publishRepository;

	@Override
	public ResponseEntity<ResponseStructure<PublishResponse>> publishBlogPost(PublishRequest publishRequest,
			int postId) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return blogPostRepository.findById(postId).map(post -> {
			if(!post.getBlog().getUser().getEmail().equals(email))
				throw new IllegalAccessRequestException("failed to publish the blog post");
			if(post.getPostType()!=PostType.DRAFT && post.getPostType()!=null)
				throw new IllegalAccessRequestException("Post is not in DRAFT state");
			Publish publish = mapToPublishEntity(publishRequest, new Publish());
			publish.setBlogPost(post);
			post.setPostType(PostType.PUBLISHED);
			publishRepository.save(publish);
			return ResponseEntity.ok(structure.setStatus(HttpStatus.CREATED.value())
					.setMessage("Blog Post is PUBLISHED").setData(mapToPublishResponse(publish)));	
		}).orElseThrow(() -> new BlogPostNotFoundByIdException("Invalid input"));
	}

	private PublishResponse mapToPublishResponse(Publish publish) {
		return PublishResponse.builder().publishId(publish.getPublishId()).seoTitle(publish.getSeoTitle())
				.seoDescription(publish.getSeoDescription()).seoTags(publish.getSeoTags())
				.build();
	}

	private Publish mapToPublishEntity(PublishRequest publishRequest, Publish publish) {
		publish.setSeoTitle(publishRequest.getSeoTitle());
		publish.setSeoDescription(publishRequest.getSeoDescription());
		publish.setSeoTags(publishRequest.getSeoTags());
		publish.setPublishURL(publishRequest.getPublishURL());
		return publish;
	}
}
