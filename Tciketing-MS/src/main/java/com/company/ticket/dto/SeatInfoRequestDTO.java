package com.company.ticket.dto;

import java.io.Serializable;
import java.util.List;

import com.company.ticket.model.SeatInfo;

import lombok.Data;

@Data
public class SeatInfoRequestDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2083966076195663230L;
	List<SeatInfo> seatInfoList;
}
