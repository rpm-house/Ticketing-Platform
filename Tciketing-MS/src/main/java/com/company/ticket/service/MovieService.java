package com.company.ticket.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.company.ticket.model.Movie;

@Service
public interface MovieService {

	public Movie save(Movie movie);

	public Movie findById(Long id);

	public List<Movie> findAll();

	public void deleteById(Long id);

	public Movie update(Long id, Movie movie);

	public Movie findByName(String name);

	public Movie findByNameAndDate(String name, String date);

}
