package com.wf.interview.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.wf.interview.exception.model.ExceptionResponse;

// @ControllerAdvice (View Based MVC application)
@RestControllerAdvice  // proxy (AOP)
public class GlobalHandler {

	// Exception Handler Method
	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handler(StudentNotFoundException ex) {
		ExceptionResponse exResponse =
				new ExceptionResponse();
		ResponseEntity<ExceptionResponse> response = 
				new ResponseEntity<ExceptionResponse>(exResponse, HttpStatus.NOT_FOUND);
		return response;
	}
	
	// Exception Handler Method
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> handler(Exception ex) {
		ExceptionResponse exResponse =
				new ExceptionResponse();
		ResponseEntity<ExceptionResponse> response = 
				new ResponseEntity<ExceptionResponse>(exResponse, HttpStatus.BAD_REQUEST);
		return response;
	}
}
