package com.company.security.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

	@GetMapping("/hello")
	public ResponseEntity<String> hi() {
		log.info("hi");
		return new ResponseEntity<>("hello", HttpStatus.OK);
	}
}
