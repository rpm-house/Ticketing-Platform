package com.company.ticket.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.ticket.model.Theatre;
import com.company.ticket.service.TheatreService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/theatre")
@PreAuthorize("hasRole('PARTNER')")
public class TheatreController {

	@Autowired
	private TheatreService theatreService;

	@PostMapping("/save")
	public ResponseEntity<Theatre> save(@RequestBody Theatre theatre) {
		log.info("");
		return new ResponseEntity<>(theatreService.save(theatre), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Theatre> get(@PathVariable Long id) {
		return new ResponseEntity<>(theatreService.findById(id), HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<Theatre>> getAll() {
		return new ResponseEntity<>(theatreService.findAll(), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Theatre> update(@PathVariable Long id, @RequestBody Theatre theatre) {
		return new ResponseEntity<>(theatreService.update(id, theatre), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		theatreService.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/test")
	public String hi() {
		log.info("hello");
		return "hello";
	}
}
