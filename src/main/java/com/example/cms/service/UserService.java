package com.example.cms.service;

import org.springframework.http.ResponseEntity;

import com.example.cms.dto.UserRequest;
import com.example.cms.dto.UserResponse;
import com.example.cms.model.User;
import com.example.cms.utility.ResponseStructure;

import jakarta.validation.Valid;

public interface UserService {
	
	public ResponseEntity<ResponseStructure<UserResponse>> userRegistration(UserRequest userRequest);   // saving/register user in database

}
