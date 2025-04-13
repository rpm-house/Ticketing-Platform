package com.company.ticket.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.ticket.model.Seat;

public interface SeatRepository extends JpaRepository<Seat,Long> {

}
