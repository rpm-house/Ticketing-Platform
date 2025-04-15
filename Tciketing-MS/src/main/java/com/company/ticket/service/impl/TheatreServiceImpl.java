package com.company.ticket.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.ticket.model.Screen;
import com.company.ticket.model.Theatre;
import com.company.ticket.repo.ScreenRepository;
import com.company.ticket.repo.SeatRepository;
import com.company.ticket.repo.TheatreRepository;
import com.company.ticket.service.TheatreService;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	@Cacheable(value = "theatre", key = "#id")
	public Theatre findById(Long id) {
		return theatreRepository.findById(id).orElse(new Theatre());
	}

	@Override
	public List<Theatre> findAll() {
		return theatreRepository.findAll();
	}

	@Override
	@CachePut(value = "theatre", key = "#id")
	public Theatre update(Long id, Theatre theatre) {
		Theatre existingTheatre = theatreRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
		log.info("existing: {}", existingTheatre);
		BeanUtils.copyProperties(theatre, existingTheatre);
		Set<Screen> screenSet = existingTheatre.getScreens().stream().map(existingScreen -> {
			theatre.getScreens().stream().filter(screen -> screen.getId().equals(existingScreen.getId())).findFirst()
					.ifPresent(screen -> BeanUtils.copyProperties(screen, existingScreen));
			return existingScreen;
		}).collect(Collectors.toSet());
		existingTheatre.setScreens(screenSet);
		screenRepository.saveAll(screenSet);
		return theatreRepository.save(existingTheatre);
	}

	@Override
	@CacheEvict(value = "theatre", key = "#id")
	public void deleteById(Long id) {
		theatreRepository.deleteById(id);

	}

}
