package com.example.cms.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.cms.exception.BlogAlreadyExistByTitleException;
import com.example.cms.exception.BlogNotFoundByIdException;
import com.example.cms.exception.BlogPostAlreadyExistsByTitleException;
import com.example.cms.exception.IllegalAccessRequestException;
import com.example.cms.exception.PanelNotFoundByIdException;
import com.example.cms.exception.TopicNotSpecifiedException;
import com.example.cms.exception.UserAlreadyExistByEmailException;
import com.example.cms.exception.UserNotFoundByIdException;

import lombok.AllArgsConstructor;

@RestControllerAdvice
@AllArgsConstructor
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	private ErrorStructure<String> Structure;
	
	private ResponseEntity<ErrorStructure<String>> errorResponse(
			HttpStatus status, String message, String rootCause)
	{
		return new ResponseEntity<ErrorStructure<String>> (Structure 
				.setStatus(status.value())
				.setMessage(message)
				.setRootCause(rootCause), status);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleUserAlreadyExistByEmail(
			UserAlreadyExistByEmailException ex){
		return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), 
				"User already exists with the given email ID");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleUserNotFoundById(
			UserNotFoundByIdException ex){
		return errorResponse(HttpStatus.NOT_FOUND, ex.getMessage(),
						"User NOT FOUND by given ID");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleBlogAlreadyExistByTitle(
			BlogAlreadyExistByTitleException ex){
		return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), 
				"Blog already exists with the given Title");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleTopicNotSpecified(
			TopicNotSpecifiedException ex){
		return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), 
				"Topic Not specified");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleBlogNotFoundById(
			BlogNotFoundByIdException ex){
		return errorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), 
				"Blog NOT FOUND by given ID");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handlePanelNotFoundById(
			PanelNotFoundByIdException ex){
		return errorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), 
				"Panel NOT FOUND by given ID");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleIllegalAccessRequest(
			IllegalAccessRequestException ex){
		return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), 
				"Illegal Access Request");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleBlogPostAlreadyExistsByTitle(
			BlogPostAlreadyExistsByTitleException ex){
		return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), 
				"BlogPost already exists with the given Title");
	}
}
