package com.example.cms.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.cms.dto.PublishRequest;
import com.example.cms.dto.PublishResponse;
import com.example.cms.service.PublishService;
import com.example.cms.utility.ResponseStructure;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@AllArgsConstructor
public class PublishController {
	
	private PublishService publishService;
	
	@PostMapping("/blog-posts/{postId}/publishes")
	public ResponseEntity<ResponseStructure<PublishResponse>> publishBlogPost(@RequestBody PublishRequest publishRequest,@PathVariable int postId){
		return publishService.publishBlogPost(publishRequest, postId);
	}
}
