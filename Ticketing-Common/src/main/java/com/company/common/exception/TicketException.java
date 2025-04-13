package com.company.common.exception;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class TicketException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1560078130565629287L;

	Map<String, Object> errorMessage = new HashMap<>();
	
	public TicketException(String message) {
		//super(message);
		errorMessage.put("message", "An error occurred");
		errorMessage.put("error", message);
	}
	
	

}
