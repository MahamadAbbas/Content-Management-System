package com.example.cms.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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
				"User Already exists with the given email ID");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleUserNotFoundById(
			UserNotFoundByIdException ex){
				return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(),
						"User NOT FOUND by given ID");
		
	}
	
}
