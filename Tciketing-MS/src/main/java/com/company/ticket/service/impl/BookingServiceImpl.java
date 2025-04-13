package com.company.ticket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.security.model.User;
import com.company.security.repo.UserRepository;
import com.company.ticket.model.Booking;
import com.company.ticket.model.Screening;
import com.company.ticket.repo.BookingRepositry;
import com.company.ticket.repo.ScreeningRepository;
import com.company.ticket.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	BookingRepositry bookingRepositry;
	
	@Autowired
	ScreeningRepository screeningRepository;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public Booking save(Booking booking) {
		User user = userRepository.findById(booking.getUserId()).get();
		Screening screening =  screeningRepository.findById(booking.getScreeningId()).get();
		booking.setUser(user);
		booking.setScreening(screening);
		return bookingRepositry.save(booking);
	}

	@Override
	public Booking findById(Long id) {
		return bookingRepositry.findById(id).orElse(new Booking());
	}

	@Override
	public List<Booking> findAll() {
		return bookingRepositry.findAll();
	}

	@Override
	public Booking update(Long id, Booking booking) {
		return bookingRepositry.save(booking);
	}

	@Override
	public void deleteById(Long id) {
		bookingRepositry.deleteById(id);
	}

}
