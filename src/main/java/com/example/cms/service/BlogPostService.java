package com.example.cms.service;

import org.springframework.http.ResponseEntity;

import com.example.cms.dto.BlogPostRequest;
import com.example.cms.dto.BlogPostResponse;
import com.example.cms.utility.ResponseStructure;

public interface BlogPostService {

	ResponseEntity<ResponseStructure<BlogPostResponse>> createDraft(BlogPostRequest blogPostRequest, int blogId);

}
