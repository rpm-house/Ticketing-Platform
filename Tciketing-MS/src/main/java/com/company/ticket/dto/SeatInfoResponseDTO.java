package com.company.ticket.dto;

import java.io.Serializable;
import java.util.List;

import com.company.ticket.model.SeatInfo;

import lombok.Data;

@Data
public class SeatInfoResponseDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5536366990833622418L;

	List<SeatInfo> bookedSeatInfoList;
	
	List<SeatInfo> availableSeatInfoList;
}
