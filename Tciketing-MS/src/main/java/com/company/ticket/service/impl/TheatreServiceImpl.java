package com.company.ticket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.ticket.model.Theatre;
import com.company.ticket.repo.ScreenRepository;
import com.company.ticket.repo.SeatRepository;
import com.company.ticket.repo.TheatreRepository;
import com.company.ticket.service.TheatreService;

@Service
public class TheatreServiceImpl implements TheatreService {

	@Autowired
	TheatreRepository theatreRepository;
	
	@Autowired
	ScreenRepository screenRepository;
	
	@Autowired
	SeatRepository seatRepository;
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public Theatre save(Theatre theatre) {
		/*
		 * for (Screen screen :theatre.getScreens()) {
		 * seatRepository.saveAll(screen.getSeats()); }
		 */
		screenRepository.saveAll(theatre.getScreens());
		return theatreRepository.save(theatre);
	}

	@Override
	public Theatre findById(Long id) {
		return theatreRepository.findById(id).orElse(new Theatre());
	}

	@Override
	public List<Theatre> findAll() {
		return theatreRepository.findAll();
	}

	@Override
	public Theatre update(Long id, Theatre theatre) {
		return theatreRepository.save(theatre);
	}

	@Override
	public void deleteById(Long id) {
		theatreRepository.deleteById(id);

	}

}
