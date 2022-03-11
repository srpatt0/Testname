package com.example.notesservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.validation.FieldError;

import com.example.notesservice.dto.NotesExceptionResponse;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NotesException.class)
	public ResponseEntity<NotesExceptionResponse> NotesHandler(NotesException ex){
		NotesExceptionResponse resp = 
				new NotesExceptionResponse(ex.getMessage(),System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value());
		
		ResponseEntity<NotesExceptionResponse> response = 
				new ResponseEntity<NotesExceptionResponse>(resp, HttpStatus.BAD_REQUEST);
		return response;
    }
    
     @ExceptionHandler(value = NoteIdNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleNoteIdNotFoundException(NoteIdNotFoundException e) {
        CustomErrorResponse error = new CustomErrorResponse("NOT_FOUND_ERROR", e.getMessage());
        error.setTimestamp(LocalDateTime.now());
        error.setStatus((HttpStatus.NOT_FOUND.value()));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InvalidNoteDataException.class)
    public ResponseEntity<CustomErrorResponse> handleInvalidNoteDataException(InvalidNoteDataException e) {
        CustomErrorResponse error = new CustomErrorResponse("IN_CORRECT_DATA", e.getMessage());
        error.setTimestamp(LocalDateTime.now());
        error.setStatus((HttpStatus.NOT_FOUND.value()));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
	
	
}