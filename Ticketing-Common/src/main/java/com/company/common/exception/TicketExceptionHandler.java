package com.company.common.exception;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.NoSuchElementException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class TicketExceptionHandler {

	@ExceptionHandler(SignatureException.class)
	public ResponseEntity<?> handleGeneric(SignatureException ex) throws JsonProcessingException {
		log.error("Error on TicketExceptionHandler SignatureException: {}", ex.getMessage());
		log.debug("Error on TicketExceptionHandler SignatureException: {}", ex);
		ErrorResponse errorResponse = new ErrorResponse(ex);
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(SecurityUtils.convertObjectToJson(errorResponse));
	}

	@ExceptionHandler(JwtException.class)
	public ResponseEntity<String> handleJwtException(JwtException ex) throws JsonProcessingException {
		log.error("Error on TicketExceptionHandler JwtException: {}", ex.getMessage());
		log.debug("Error on TicketExceptionHandler JwtException: {}", ex);
		ErrorResponse errorResponse = new ErrorResponse(ex);
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(SecurityUtils.convertObjectToJson(errorResponse));
	}

	@ExceptionHandler(AuthorizationDeniedException.class)
	public ResponseEntity<?> handleGeneric(AuthorizationDeniedException ex) throws JsonProcessingException {
		log.error("Error on TicketExceptionHandler AuthorizationDeniedException: {}", ex.getMessage());
		log.debug("Error on TicketExceptionHandler AuthorizationDeniedException: {}", ex);
		ErrorResponse errorResponse = new ErrorResponse(ex);
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(SecurityUtils.convertObjectToJson(errorResponse));
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<?> handleTicketException(DataIntegrityViolationException ex) throws JsonProcessingException {
		log.error("Error on TicketExceptionHandler DataIntegrityViolationException: {}", ex.getMessage());
		log.debug("Error on TicketExceptionHandler DataIntegrityViolationException: {}", ex);
		ErrorResponse errorResponse = new ErrorResponse(ex);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(SecurityUtils.convertObjectToJson(errorResponse));

	}
	
	@ExceptionHandler(TransactionSystemException.class)
	public ResponseEntity<?> handleTicketException(TransactionSystemException ex) throws JsonProcessingException {
		log.error("Error on TicketExceptionHandler TransactionSystemException: {}", ex.getMessage());
		log.debug("Error on TicketExceptionHandler TransactionSystemException: {}", ex);
		ErrorResponse errorResponse = new ErrorResponse(ex);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(SecurityUtils.convertObjectToJson(errorResponse));

	}

	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<?> handleTicketException(SQLIntegrityConstraintViolationException ex) throws JsonProcessingException {
		log.error("Error on TicketExceptionHandler SQLIntegrityConstraintViolationException: {}", ex.getMessage());
		log.debug("Error on TicketExceptionHandler SQLIntegrityConstraintViolationException: {}", ex);
		ErrorResponse errorResponse = new ErrorResponse(ex);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(SecurityUtils.convertObjectToJson(errorResponse));

	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> handleTicketException(EntityNotFoundException ex) throws JsonProcessingException {
		log.error("Error on TicketExceptionHandler EntityNotFoundException: {}", ex.getMessage());
		log.debug("Error on TicketExceptionHandler EntityNotFoundException: {}", ex);
		ErrorResponse errorResponse = new ErrorResponse(ex);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(SecurityUtils.convertObjectToJson(errorResponse));

	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handleTicketException(ConstraintViolationException ex) throws JsonProcessingException {
		log.error("Error on TicketExceptionHandler ConstraintViolationException: {}", ex.getMessage());
		log.debug("Error on TicketExceptionHandler ConstraintViolationException: {}", ex);
		ErrorResponse errorResponse = new ErrorResponse(ex);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(SecurityUtils.convertObjectToJson(errorResponse));

	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<?> handleTicketException(NoSuchElementException ex) throws JsonProcessingException {
		log.error("Error on TicketExceptionHandler DataIntegrityViolationException: {}", ex.getMessage());
		log.debug("Error on TicketExceptionHandler DataIntegrityViolationException: {}", ex);
		ErrorResponse errorResponse = new ErrorResponse(ex);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(SecurityUtils.convertObjectToJson(errorResponse));

	}
	
	@ExceptionHandler(JsonProcessingException.class)
	public ResponseEntity<?> handleGeneric(JsonProcessingException ex) {
		log.error("Error on TicketExceptionHandler JsonProcessingException: {}", ex.getMessage());
		log.debug("Error on TicketExceptionHandler JsonProcessingException: {}", ex);
		ErrorResponse errorResponse = new ErrorResponse(ex);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse.toString());
	}

	@ExceptionHandler(TicketException.class)
	public ResponseEntity<?> handleTicketException(TicketException ex) throws JsonProcessingException {
		log.error("Error on TicketExceptionHandler TicketException: {}", ex.getError());
		log.debug("Error on TicketExceptionHandler TicketException: {}", ex);
		ErrorResponse errorResponse = new ErrorResponse(ex);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(SecurityUtils.convertObjectToJson(errorResponse));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGeneric(Exception ex) throws JsonProcessingException {
		log.error("Error on TicketExceptionHandler Exception: {}", ex.getMessage());
		log.debug("Error on TicketExceptionHandler Exception: {}", ex);
		ErrorResponse errorResponse = new ErrorResponse(ex);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(SecurityUtils.convertObjectToJson(errorResponse));
	}

}
