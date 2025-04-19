package com.company.ticket.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.security.model.User;
import com.company.ticket.dto.BookingRequestDTO;
import com.company.ticket.dto.BookingResponseDTO;
import com.company.ticket.model.Booking;
import com.company.ticket.model.Screen;
import com.company.ticket.model.Screening;
import com.company.ticket.repo.BookingRepository;
import com.company.ticket.repo.ScreenRepository;
import com.company.ticket.repo.ScreeningRepository;
import com.company.ticket.service.BookingService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

public class BookingServiceImpl implements BookingService {

	@Autowired
	BookingRepository bookingRepository;

	@Autowired
	ScreeningRepository screeningRepository;

	@Autowired
	ScreenRepository screenRepository;
	

	@Override
	public BookingResponseDTO save(BookingRequestDTO bookingDTO) {
		Booking booking = new Booking();
		booking.setSeatInfoList(bookingDTO.getSeatInfoList());
		booking.setUser(new User(bookingDTO.getUserId()));
		booking.setScreening(new Screening(bookingDTO.getScreeningId()));
		BookingResponseDTO bookingResponseDTO = new BookingResponseDTO();
		Booking bookingResponse  = bookingRepository.save(booking);
		
		Screening screening  = screeningRepository.findById(bookingDTO.getScreeningId()).get();
		Screen screen = screenRepository.findById(screening.getScreenId()).get();
		screen.setBlockedSeats(bookingDTO.getSeatInfoList().size());
		screenRepository.save(screen);
		
		BeanUtils.copyProperties(bookingDTO, bookingResponseDTO);
		bookingResponseDTO.setBookingId(bookingResponse.getId());
		return bookingResponseDTO;
	}

	@Override
	public Booking findById(Long id) {
		return bookingRepository.findById(id).orElse(new Booking());
	}

	@Override
	public List<Booking> findAll() {
		return bookingRepository.findAll();
	}

	@Override
	public Booking update(Long id, Booking booking) {
		return bookingRepository.save(booking);
	}

	@Override
	public void deleteById(Long id) {
		bookingRepository.deleteById(id);
	}

}
