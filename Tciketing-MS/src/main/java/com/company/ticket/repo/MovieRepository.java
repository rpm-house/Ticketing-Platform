package com.company.ticket.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.ticket.model.Movie;

public interface MovieRepository extends JpaRepository<Movie,Long> {

	public Movie findByName(String name);

}
