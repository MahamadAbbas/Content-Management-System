package com.example.cms.serviceimpl;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.cms.dto.BlogRequest;
import com.example.cms.dto.BlogResponse;
import com.example.cms.exception.BlogAlreadyExistByTitleException;
import com.example.cms.exception.BlogNotFoundByIdException;
import com.example.cms.exception.TopicNotSpecifiedException;
import com.example.cms.exception.UserNotFoundByIdException;
import com.example.cms.model.Blog;
import com.example.cms.repository.BlogRepository;
import com.example.cms.repository.UserRepository;
import com.example.cms.service.BlogService;
import com.example.cms.utility.ResponseStructure;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BlogServiceImpl implements BlogService {

	private BlogRepository blogRepository;
	private ResponseStructure<BlogResponse> structure;
	private UserRepository userRepository;

	@Override
	public ResponseEntity<ResponseStructure<BlogResponse>> createBlog(BlogRequest blogRequest, int userId) {
		return userRepository.findById(userId).map(user -> {
			if (blogRepository.existsByTitle(blogRequest.getTitle()))
				throw new BlogAlreadyExistByTitleException("Title Already Exists(Failed to create blog)");

			if(blogRequest.getTopics().length<1)
				throw new TopicNotSpecifiedException("Failed to create blog");

			Blog blog = mapToBlogEntity(blogRequest,new Blog());
			blog.setListUser(Arrays.asList(user));
			blogRepository.save(blog);
			return ResponseEntity.ok(structure.setStatus(HttpStatus.OK.value()).setMessage("Blog Created Successfully")
					.setData(mapToBlogResponse(blog)));
		}).orElseThrow(() -> new UserNotFoundByIdException("User ID NOT Found(Failed to create blog)"));
	}

	private BlogResponse mapToBlogResponse(Blog blog) {
		return BlogResponse.builder().blogId(blog.getBlogId()).title(blog.getTitle()).topics(blog.getTopics())
				.about(blog.getAbout()).listUser(blog.getListUser())
				.build();
	}

	private Blog mapToBlogEntity(BlogRequest blogRequest, Blog blog) {
		blog.setTitle(blogRequest.getTitle());
		blog.setTopics(blogRequest.getTopics());
		blog.setAbout(blogRequest.getAbout());
		return blog;
	}

	@Override
	public boolean checkForBlogTitleAvailability(String title) {
		return blogRepository.existsByTitle(title);
	}

	@Override
	public ResponseEntity<ResponseStructure<BlogResponse>> findBlogById(int blogId) {
		return blogRepository.findById(blogId)
				.map(blog -> ResponseEntity.ok(structure.setStatus(HttpStatus.OK.value())
						.setMessage("Blog fetched Successfully").setData(mapToBlogResponse(blog))))
				.orElseThrow(() -> new BlogNotFoundByIdException("Invalid blogId"));
	}

	@Override
	public ResponseEntity<ResponseStructure<BlogResponse>> updateBlogData(int blogId, BlogRequest blogRequest) {
		return blogRepository.findById(blogId).map(blog -> {
			if (blogRepository.existsByTitle(blogRequest.getTitle()))
				throw new BlogAlreadyExistByTitleException("Title Already Exists(Failed to create blog)");

			if(blogRequest.getTopics().length<1)
				throw new TopicNotSpecifiedException("Failed to create blog");
			
			blog.setTitle(blogRequest.getTitle());
			blog.setTopics(blogRequest.getTopics());
			blog.setAbout(blogRequest.getAbout());
			Blog saveBlog = blogRepository.save(blog);
			return ResponseEntity.ok(structure.setStatus(HttpStatus.OK.value())
					.setMessage("Blog Updated Successfully").setData(mapToBlogResponse(saveBlog)));
		}).orElseThrow(() -> new BlogNotFoundByIdException("Invalid blogId (Failed to create blog)"));
	}
}