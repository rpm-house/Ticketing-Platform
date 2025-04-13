package com.company.ticket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.ticket.model.Screening;
import com.company.ticket.model.Seat;
import com.company.ticket.repo.ScreeningRepository;
import com.company.ticket.repo.SeatRepository;
import com.company.ticket.service.SeatService;

@Service
public class SeatServiceImpl implements SeatService {

	@Autowired
	SeatRepository seatRepository;

	@Autowired
	ScreeningRepository screeningRepository;

	@Override
	public Seat save(Seat seat) {
		Screening screening = screeningRepository.findById(seat.getScreeningId()).get();
		seat.setScreening(screening);
		return seatRepository.save(seat);
	}

	@Override
	public Seat findById(Long id) {
		return seatRepository.findById(id).orElse(new Seat());
	}

	@Override
	public List<Seat> findAll() {
		return seatRepository.findAll();
	}

	@Override
	public Seat update(Long id, Seat seat) {
		return seatRepository.save(seat);
	}

	@Override
	public void deleteById(Long id) {
		seatRepository.deleteById(id);

	}

}
