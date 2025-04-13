package com.company.common.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class ErrorResponse {

	String message;
	int status;

	/*
	 * public ErrorResponse(Exception exception) { this.message =
	 * exception.getMessage(); this.status =
	 * HttpStatus.INTERNAL_SERVER_ERROR.value(); }
	 */

	public ErrorResponse(Exception exception) {
		List<StackTraceElement> list = Arrays.stream(exception.getStackTrace())
                .filter(se -> se.getClassName().startsWith("com.company")) //or any other filter criteria
                .collect(Collectors.toList());
                
		log.info("getStackTrace : "+list);
		this.message = exception.getCause().toString();
		this.status = HttpStatus.INTERNAL_SERVER_ERROR.value();
	}
}
