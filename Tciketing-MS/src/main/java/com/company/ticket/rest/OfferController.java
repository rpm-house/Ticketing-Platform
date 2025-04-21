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

import com.company.ticket.dto.OfferRequestDTO;
import com.company.ticket.dto.OfferResponseDTO;
import com.company.ticket.model.Offer;
import com.company.ticket.service.OfferService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/offer")
@PreAuthorize("hasRole('PARTNER')")
public class OfferController {

	@Autowired
	private OfferService offerService;

	@PostMapping("/save")
	public ResponseEntity<OfferResponseDTO> save(@RequestBody OfferRequestDTO offer) {
		log.info("");
		return new ResponseEntity<>(offerService.save(offer), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Offer> get(@PathVariable Long id) {
		return new ResponseEntity<>(offerService.findById(id), HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<Offer>> getAll() {
		return new ResponseEntity<>(offerService.findAll(), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<OfferResponseDTO> update(@PathVariable Long id, @RequestBody OfferRequestDTO offerRequestDTO) {
		return new ResponseEntity<>(offerService.update(id, offerRequestDTO), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		offerService.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/test")
	public String hi() {
		log.info("hello");
		return "hello";
	}
}
