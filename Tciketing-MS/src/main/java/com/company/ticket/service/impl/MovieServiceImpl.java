package com.company.ticket.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.ticket.model.Movie;
import com.company.ticket.model.Screening;
import com.company.ticket.repo.MovieRepository;
import com.company.ticket.repo.ScreeningRepository;
import com.company.ticket.repo.TheatreRepository;
import com.company.ticket.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	MovieRepository movieRepository;

	@Autowired
	ScreeningRepository screeningRepository;

	@Autowired
	TheatreRepository theatreRepository;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Movie save(Movie movie) {
		List<Screening> screenings = movie.getScreenings().stream().map(s -> {
			s.setTheatre(theatreRepository.findById(s.getTheatreId()).get());
			Integer tickets = s.getTheatre().getScreens().stream().map(x -> x.getTotalSeats()).reduce(0, Integer::sum);
			s.setAvailableTickets(tickets);
			return s;
		}).collect(Collectors.toList());

		screeningRepository.saveAll(screenings);
		return movieRepository.save(movie);
	}

	@Override
	public Movie findById(Long id) {
		return movieRepository.findById(id).orElse(new Movie());
	}

	@Override
	public Movie findByName(String name) {
		return movieRepository.findByName(name);
	}

	@Override
	public Movie findByNameAndDate(String name, String date) {
		Movie movie = movieRepository.findByName(name);
		Set<Screening> screenings = movie.getScreenings().stream().filter(s -> s.getScreeningDate().contains(date))
				.collect(Collectors.toSet());
		movie.setScreenings(screenings);
		return movie;
	}

	@Override
	public List<Movie> findAll() {
		return movieRepository.findAll();
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Movie update(Long id, Movie movie) {
		Movie existingMovie  =  movieRepository.findById(id).get();
		BeanUtils.copyProperties(movie, existingMovie);
		return save(existingMovie);
	}

	@Override
	public void deleteById(Long id) {
		movieRepository.deleteById(id);

	}

}
