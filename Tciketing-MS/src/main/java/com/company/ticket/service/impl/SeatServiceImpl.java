package com.company.ticket.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.ticket.dto.SeatInfoRequestDTO;
import com.company.ticket.dto.SeatInfoResponseDTO;
import com.company.ticket.model.Booking;
import com.company.ticket.model.Screen;
import com.company.ticket.model.Screening;
import com.company.ticket.model.Seat;
import com.company.ticket.model.SeatInfo;
import com.company.ticket.repo.BookingRepository;
import com.company.ticket.repo.ScreenRepository;
import com.company.ticket.repo.ScreeningRepository;
import com.company.ticket.repo.SeatRepository;
import com.company.ticket.service.SeatService;

@Service
public class SeatServiceImpl implements SeatService {

	@Autowired
	SeatRepository seatRepository;

	@Autowired
	ScreeningRepository screeningRepository;

	@Autowired
	ScreenRepository screenRepository;

	@Autowired
	BookingRepository bookingRepository;

	@Override
	public Seat save(Seat seat) {
		Screening screening = screeningRepository.findById(seat.getScreeningId()).get();
		seat.setScreening(screening);
		return seatRepository.save(seat);
	}

	@Override
	public Seat findById(Long id) {
		return seatRepository.findById(id).orElse(new Seat());
	}

	@Override
	public List<Seat> findAll() {
		return seatRepository.findAll();
	}

	@Override
	public Seat update(Long id, Seat seat) {
		return seatRepository.save(seat);
	}

	@Override
	public void deleteById(Long id) {
		seatRepository.deleteById(id);

	}

	@Override
	public List<SeatInfo> getAvailableSeats(Long screeningId) {
		Screening screening = screeningRepository.findById(screeningId).get();
		Screen screen = screenRepository.findById(screening.getScreenId()).get();
		List<SeatInfo> totalSeats = screen.getSeatInfoList();
		Booking booking = bookingRepository.findByScreening(screening).get();
		List<SeatInfo> bookedSeats = booking.getSeatInfoList();
		List<SeatInfo> availableSeats = new ArrayList<>(totalSeats);
		availableSeats.removeAll(bookedSeats);
		return availableSeats;
	}

	@Override
	public SeatInfoResponseDTO validate(Long screeningId, SeatInfoRequestDTO seatInfoRequestDTO) {

		SeatInfoResponseDTO responseDTO = new SeatInfoResponseDTO();
		List<SeatInfo> availableSeats = getAvailableSeats(screeningId);
		List<SeatInfo> requestedSeats = seatInfoRequestDTO.getSeatInfoList();

		List<SeatInfo> actuallyAvailable = requestedSeats.stream().filter(availableSeats::contains)
				.collect(Collectors.toList());

		List<SeatInfo> unavailable = requestedSeats.stream().filter(seat -> !availableSeats.contains(seat))
				.collect(Collectors.toList());

		responseDTO.setAvailableSeatInfoList(actuallyAvailable);
		responseDTO.setBookedSeatInfoList(unavailable);

		return responseDTO;
	}

}
