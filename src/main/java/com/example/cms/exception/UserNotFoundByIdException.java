package com.example.cms.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@SuppressWarnings("serial")
public class UserNotFoundByIdException extends RuntimeException {
	
	private String message;

}
