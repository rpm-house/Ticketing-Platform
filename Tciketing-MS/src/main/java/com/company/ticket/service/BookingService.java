package com.company.ticket.service;

import java.util.List;

import com.company.ticket.model.Booking;

public interface BookingService {

	public Booking save(Booking booking);

	public Booking  findById(Long id);

	public List<Booking> findAll();

	public Booking update(Long id, Booking booking);

	public void deleteById(Long id);

}
