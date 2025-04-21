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

import com.company.ticket.dto.SeatInfoRequestDTO;
import com.company.ticket.dto.SeatInfoResponseDTO;
import com.company.ticket.model.Seat;
import com.company.ticket.model.SeatInfo;
import com.company.ticket.service.SeatService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/seat")
public class SeatController {

	@Autowired
	private SeatService seatService;

	@PostMapping("/save")
	public ResponseEntity<String> save() {
		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("This endpoint is not implemented yet.");
	}

	@GetMapping("/{id}")
	public ResponseEntity<String> get(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("This endpoint is not implemented yet.");
	}

	@GetMapping("/")
	public ResponseEntity<String> getAll() {
		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("This endpoint is not implemented yet.");
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Seat seat) {
		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("This endpoint is not implemented yet.");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("This endpoint is not implemented yet.");
	}

	@GetMapping("/available/{screeningId}")
	public ResponseEntity<List<SeatInfo>> getAvailableSeats(@PathVariable("screeningId") Long screeningId) {
		return new ResponseEntity<>(seatService.getAvailableSeats(screeningId), HttpStatus.OK);
	}

	@PostMapping("/validate/{screeningId}")
	public ResponseEntity<SeatInfoResponseDTO> validate(@PathVariable("screeningId") Long screeningId,
			@RequestBody SeatInfoRequestDTO seatInfoRequestDTO) {
		return new ResponseEntity<>(seatService.validate(screeningId, seatInfoRequestDTO), HttpStatus.OK);
	}

}
