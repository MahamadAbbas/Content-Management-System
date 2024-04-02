package com.example.cms.serviceimpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.cms.dto.BlogPostRequest;
import com.example.cms.dto.BlogPostResponse;
import com.example.cms.enums.PostType;
import com.example.cms.exception.BlogNotFoundByIdException;
import com.example.cms.exception.BlogPostNotFoundByIdException;
import com.example.cms.exception.UserNotFoundByIdException;
import com.example.cms.model.BlogPost;
import com.example.cms.repository.BlogPostRepository;
import com.example.cms.repository.BlogRepository;
import com.example.cms.repository.ContributionPanelRepository;
import com.example.cms.repository.UserRepository;
import com.example.cms.service.BlogPostService;
import com.example.cms.utility.ResponseStructure;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BlogPostServiceImpl implements BlogPostService{
	
	private BlogPostRepository blogPostRepository;
	private ResponseStructure<BlogPostResponse> structure;
	private BlogRepository blogRepository;
	private ContributionPanelRepository contributionPanelRepository;
	private UserRepository userRepository;

	@Override
	public ResponseEntity<ResponseStructure<BlogPostResponse>> createDraft(BlogPostRequest blogPostRequest, int blogId) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return userRepository.findByEmail(email).map(user -> {
			return blogRepository.findById(blogId).map(blog -> {
				if(!blog.getUser().getEmail().equals(email) && 
						contributionPanelRepository.existsByPanelIdAndContributors(blog.getContributionPanel().getPanelId(),user))
					throw new UserNotFoundByIdException("Invalid Input");
				return blogRepository.findById(blogId).map(blog2 ->{
					BlogPost blogPost2 = mapToBlogPostEntity(blogPostRequest, new BlogPost());
					blogPost2.setBlog(blog2);
					blogPost2.setPostType(PostType.DRAFT);
				    return ResponseEntity.ok(structure.setStatus(HttpStatus.OK.value())
				    		.setMessage("Blog post data is saved successfully")
				    		.setData(mapToBlogPostResponse(blogPostRepository.save(blogPost2))));
				}).orElseThrow(() -> new BlogNotFoundByIdException("Invalid Blog Id"));
			}).orElseThrow(() -> new BlogNotFoundByIdException("Invalid Blog Id"));
		}).orElseThrow(() -> new UserNotFoundByIdException("Invalid Input"));
	}
	
	private BlogPostResponse mapToBlogPostResponse(BlogPost blogPost) {
		return BlogPostResponse.builder().blogPostId(blogPost.getPostId()) .title(blogPost.getTitle())
				.subTitle(blogPost.getSubTitle()).summary(blogPost.getSummary()).postType(blogPost.getPostType())
				.createdAt(blogPost.getCreatedAt()).createdBy(blogPost.getCreatedBy())
				.lastModifiedAt(blogPost.getLastModifiedAt()).lastModifiedBy(blogPost.getLastModifiedBy())
				.build();
	}
	
	private BlogPost mapToBlogPostEntity(BlogPostRequest blogPostRequest, BlogPost blogPost) {
		blogPost.setTitle(blogPostRequest.getTitle());
		blogPost.setSubTitle(blogPostRequest.getSubTitle());
		blogPost.setSummary(blogPostRequest.getSummary());
		blogPost.setPostType(PostType.DRAFT);
		return blogPost;
	}

	@Override
	public ResponseEntity<ResponseStructure<BlogPostResponse>> updateDraft(int postId,
			BlogPostRequest blogPostRequest) {
		return blogPostRepository.findById(postId).map(blogPost -> {
			return ResponseEntity.ok(structure.setStatus(HttpStatus.OK.value())
					.setMessage("Draft Updated Successfully")
					.setData(mapToBlogPostResponse(blogPostRepository.save(mapToBlogPostEntity(blogPostRequest, blogPost)))));
		}).orElseThrow(() -> new BlogPostNotFoundByIdException("Invalid Input"));
	}
}
