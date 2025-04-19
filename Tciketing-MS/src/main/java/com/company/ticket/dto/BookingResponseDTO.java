package com.company.ticket.dto;

import java.io.Serializable;
import java.util.List;

import com.company.ticket.model.SeatInfo;

import lombok.Data;

//@Builder
@Data
public class BookingResponseDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4984834779392346993L;
	private Long bookingId;
	private Long userId;
	private Long screeningId;
	private List<SeatInfo> seatInfoList;
}
