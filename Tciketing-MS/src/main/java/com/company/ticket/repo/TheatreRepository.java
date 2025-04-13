package com.company.ticket.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.ticket.model.Theatre;

public interface TheatreRepository extends JpaRepository<Theatre,Long> {

}
