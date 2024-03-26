package com.example.cms.dto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Component
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
	
	private int userId;
	private String username;
	private String email;
	
	private LocalDateTime createdAt;
	private LocalDateTime lastModifiedAt;

}
