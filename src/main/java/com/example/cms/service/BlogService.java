package com.example.cms.service;

import org.springframework.http.ResponseEntity;

import com.example.cms.dto.BlogRequest;
import com.example.cms.dto.BlogResponse;
import com.example.cms.utility.ResponseStructure;

public interface BlogService {

	ResponseEntity<ResponseStructure<BlogResponse>> createBlog(BlogRequest blogRequest, int userId);

}
