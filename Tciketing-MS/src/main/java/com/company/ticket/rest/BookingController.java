package com.company.ticket.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.ticket.dto.BookingRequestDTO;
import com.company.ticket.dto.BookingResponseDTO;
import com.company.ticket.model.Booking;
import com.company.ticket.service.BookingService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ticket")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@Operation(summary = "Ticket Booking", description = "Returns Ticket Booking Object.")
	@PostMapping("/save")
	public ResponseEntity<BookingResponseDTO> save(@RequestBody BookingRequestDTO booking) {
		log.info("Ticket Booking Initiated. : {}", booking);
		return new ResponseEntity<>(bookingService.save(booking), HttpStatus.OK);
	}

	@Operation(summary = "Ticket Booking", description = "Returns Ticket Booking Object.")
	@GetMapping("/{id}")
	public ResponseEntity<Booking> get(@PathVariable("id") Long id) {
		return new ResponseEntity<>(bookingService.findById(id), HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<Booking>> getAll() {
		return new ResponseEntity<>(bookingService.findAll(), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Booking> update(@PathVariable Long id, @RequestBody Booking booking) {
		return new ResponseEntity<>(bookingService.update(id, booking), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		bookingService.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/test")
	public String hi() {
		log.info("hello");
		return "hello";
	}

}
