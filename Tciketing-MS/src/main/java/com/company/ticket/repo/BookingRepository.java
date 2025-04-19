package com.company.ticket.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.ticket.model.Booking;
import com.company.ticket.model.Screening;

public interface BookingRepository extends JpaRepository<Booking,Long> {

	Optional<Booking> findByScreening(Screening screening);
}
