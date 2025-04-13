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

import com.company.ticket.model.Seat;
import com.company.ticket.service.SeatService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/seat")
public class SeatController {

	@Autowired
	private SeatService seatService;

	@PostMapping("/save")
	public ResponseEntity<Seat> save(@RequestBody Seat seat) {
		log.info("");
		return new ResponseEntity<>(seatService.save(seat), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Seat> get(@PathVariable("id") Long id) {
		return new ResponseEntity<>(seatService.findById(id), HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<Seat>> getAll() {
		return new ResponseEntity<>(seatService.findAll(), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Seat> update(@PathVariable Long id, @RequestBody Seat seat) {
		return new ResponseEntity<>(seatService.update(id, seat), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		seatService.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
