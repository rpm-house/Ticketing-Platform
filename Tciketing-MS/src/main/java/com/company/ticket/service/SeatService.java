package com.company.ticket.service;

import java.util.List;

import com.company.ticket.model.Seat;

public interface SeatService {

	public Seat save(Seat seat);

	public Seat findById(Long id);

	public List<Seat> findAll();

	public Seat update(Long id, Seat seat);

	public void deleteById(Long id);

}
