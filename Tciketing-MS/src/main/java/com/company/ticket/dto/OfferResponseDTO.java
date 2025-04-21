package com.company.ticket.dto;

import java.io.Serializable;

import com.company.ticket.model.Screening;

import lombok.Data;

@Data
public class OfferResponseDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2480674628216130835L;

	private Long offerId;
	
	private String name;

	private String value;
	
	private Screening screening;

}
