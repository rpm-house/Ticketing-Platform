package com.company.ticket.service;

import java.util.List;

import com.company.ticket.dto.BookingRequestDTO;
import com.company.ticket.dto.BookingResponseDTO;
import com.company.ticket.model.Booking;

public interface BookingService {

	public BookingResponseDTO save(BookingRequestDTO booking);

	public Booking  findById(Long id);

	public List<Booking> findAll();

	public BookingResponseDTO update(Long id, BookingRequestDTO bookingRequestDTO);

	public void deleteById(Long id);
	

}
