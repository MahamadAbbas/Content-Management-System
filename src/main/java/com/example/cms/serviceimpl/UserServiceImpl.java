package com.example.cms.serviceimpl;

import java.util.function.Supplier;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.cms.dto.UserRequest;
import com.example.cms.dto.UserResponse;
import com.example.cms.exception.UserAlreadyExistByEmailException;
import com.example.cms.exception.UserNotFoundByIdException;
import com.example.cms.model.User;
import com.example.cms.repository.UserRepository;
import com.example.cms.service.UserService;
import com.example.cms.utility.ResponseStructure;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private ResponseStructure<UserResponse> structure;
	private PasswordEncoder passwordEncoder;

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> userRegistration(UserRequest userRequest) {

		if (userRepository.existsByEmail(userRequest.getEmail()))
			throw new UserAlreadyExistByEmailException("Failed to register user");

		User user = userRepository.save(mapToUserEntity(userRequest, new User()));

		return ResponseEntity.ok(structure.setStatus(HttpStatus.OK.value()).setMessage("User Registered Successfully")
				.setData(mapToUserResponse(user)));
	}

	private UserResponse mapToUserResponse(User user) {
		return UserResponse.builder().userId(user.getUserId()).username(user.getUsername()).email(user.getEmail())
				.createdAt(user.getCreatedAt()).lastModifiedAt(user.getLastModifiedAt())
				.build();
	}

	private User mapToUserEntity(UserRequest userRequest, User user) {
		user.setUsername(userRequest.getUsername());
		user.setEmail(userRequest.getEmail());
		user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		user.setDeleted(false);
		return user;
	}
	
	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> findUniqueUser(int userId) {
		return userRepository.findById(userId)
				.map(user -> ResponseEntity.ok(structure.setStatus(HttpStatus.OK.value())
						.setMessage("User fetched Successfully").setData(mapToUserResponse(user))))
				.orElseThrow(() -> new UserNotFoundByIdException("Invalid UserId"));
	}
}
