package com.company.ticket.model;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1131528656273489151L;

	private String seatNo;
	private String seatType;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		SeatInfo seatInfo = (SeatInfo) o;
		return Objects.equals(seatNo, seatInfo.seatNo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(seatNo);
	}

}
