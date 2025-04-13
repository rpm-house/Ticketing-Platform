package com.company.ticket.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.ticket.model.Booking;

public interface BookingRepositry extends JpaRepository<Booking,Long> {

}
