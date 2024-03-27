package com.example.cms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.dto.UserRequest;
import com.example.cms.dto.UserResponse;
import com.example.cms.model.User;
import com.example.cms.service.UserService;
import com.example.cms.utility.ResponseStructure;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController {
	
	private UserService userService;
	
//	@Operation(description = "The endpoint is used to registration of new User to the database", responses = {
//			@ApiResponse(responseCode = "200", description = "User Registration Successfully"),
//			@ApiResponse(responseCode = "400", description = "Invalid Inputs")
//	})
	@PostMapping(value = "/users/register")
	public ResponseEntity<ResponseStructure<UserResponse>> userRegistration(@RequestBody @Valid UserRequest userRequest){
		return userService.userRegistration(userRequest);
	}
	
	@GetMapping("/test")
	public String test() {
		return "Hello From CMS";
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> findUniqueUser(@PathVariable int userId){
		return userService.findUniqueUser(userId);
	}
}
