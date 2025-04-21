package com.company.ticket.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class OfferRequestDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2480674628216130835L;
	
	private String name;

	private String value;

	private Long screeningId;

}
