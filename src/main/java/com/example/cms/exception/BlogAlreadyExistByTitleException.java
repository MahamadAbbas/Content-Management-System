package com.example.cms.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@SuppressWarnings("serial")
public class BlogAlreadyExistByTitleException extends RuntimeException {
	
	private String message;

}
