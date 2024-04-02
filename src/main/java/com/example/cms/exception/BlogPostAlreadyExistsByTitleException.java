package com.example.cms.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@SuppressWarnings("serial")
public class BlogPostAlreadyExistsByTitleException extends RuntimeException {
	
	private String message;

}
