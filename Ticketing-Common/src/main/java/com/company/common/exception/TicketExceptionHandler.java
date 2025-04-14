package com.company.common.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class TicketExceptionHandler {

	 @ExceptionHandler(TicketException.class)
	    public ResponseEntity<?> handleTicketException(TicketException ex) {
		 log.error("Error on TicketExceptionHandler Exception: {}", ex.getMessage());
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                             .body(Map.of("error", ex.getMessage()));
	    }

	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<?> handleGeneric(Exception ex) {
	    	log.error("Error on TicketExceptionHandler Exception: {}", ex.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body(Map.of("error", "Something went wrong"));
	    }
}
