package com.company.common.exception;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authorization.AuthorizationDeniedException;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.validation.ConstraintViolationException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class ErrorResponse {

	String error;
	int status;

	/*
	 * public ErrorResponse(Exception exception) { this.message =
	 * exception.getMessage(); this.status =
	 * HttpStatus.INTERNAL_SERVER_ERROR.value(); }
	 */

	public ErrorResponse(Exception exception) {
		List<StackTraceElement> list = Arrays.stream(exception.getStackTrace())
				.filter(se -> se.getClassName().startsWith("com.company")) // or any other filter criteria
				.collect(Collectors.toList());
		exception.printStackTrace();
		log.error("getStackTrace : " + list);
		log.info("exception: "+exception);
		if (null != exception.getCause()) {
			this.error = exception.getCause().toString();
		} else if(null != exception.getMessage()) {
			this.error = exception.getMessage();
		}
		if (exception instanceof SignatureException) {
			this.status = HttpStatus.UNAUTHORIZED.value();
		} else if (exception instanceof JwtException) {
			this.status = HttpStatus.UNAUTHORIZED.value();
		} else if (exception instanceof AuthorizationDeniedException) {
			this.status = HttpStatus.FORBIDDEN.value();
		} else if (exception instanceof DataIntegrityViolationException) {
			this.status = HttpStatus.NOT_FOUND.value();
		} else if (exception instanceof ConstraintViolationException) {
			this.status = HttpStatus.BAD_REQUEST.value();
		}else if (exception instanceof NoSuchElementException) {
			this.status = HttpStatus.NOT_FOUND.value();
		}else if (exception instanceof TicketException) {
			this.error = ((TicketException) exception).getError();
			this.status = ((TicketException) exception).getStatus();
		} else if (exception instanceof RuntimeException) {
			this.status = HttpStatus.INTERNAL_SERVER_ERROR.value();
		} else if (exception instanceof Exception) {
			this.status = HttpStatus.INTERNAL_SERVER_ERROR.value();
		} else {
			this.status = HttpStatus.INTERNAL_SERVER_ERROR.value();
		}
	}

	public ErrorResponse(String message) {
		this.error = message;
		this.status = HttpStatus.INTERNAL_SERVER_ERROR.value();
	}
}
