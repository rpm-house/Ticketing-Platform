package com.company.common.exception;

import lombok.Data;

@Data
public class TicketException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1560078130565629287L;

	String error;
	int status;

	public TicketException(String error, int status) {
		super();
		this.error = error;
		this.status = status;
	}

}
