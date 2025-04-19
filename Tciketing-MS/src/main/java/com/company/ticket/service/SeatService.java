package com.company.ticket.service;

import java.util.List;

import com.company.ticket.dto.SeatInfoRequestDTO;
import com.company.ticket.dto.SeatInfoResponseDTO;
import com.company.ticket.model.Seat;
import com.company.ticket.model.SeatInfo;

public interface SeatService {

	public Seat save(Seat seat);

	public Seat findById(Long id);

	public List<Seat> findAll();

	public Seat update(Long id, Seat seat);

	public void deleteById(Long id);

	public List<SeatInfo> getAvailableSeats(Long screeningId);

	public SeatInfoResponseDTO validate(Long screeningId, SeatInfoRequestDTO seatInfoRequestDTO);

}
