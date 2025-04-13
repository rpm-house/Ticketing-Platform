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

import com.company.ticket.model.Movie;
import com.company.ticket.service.MovieService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/movie")
public class MovieController {

	@Autowired
	private MovieService movieService;

	@PostMapping("/save")
	public ResponseEntity<Movie> save(@RequestBody Movie movie) {
		log.info("");
		return new ResponseEntity<>(movieService.save(movie), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Movie> get(@PathVariable("id") Long id) {
		return new ResponseEntity<>(movieService.findById(id), HttpStatus.OK);
	}
	
	@GetMapping("/byName/{name}")
	public ResponseEntity<Movie> getByName(@PathVariable("name")  String name) {
		return new ResponseEntity<>(movieService.findByName(name), HttpStatus.OK);
	}
	
	@GetMapping("/byNameAndDate/{name}/{date}")
	public ResponseEntity<Movie> getByNameAndDate(@PathVariable("name")  String name, @PathVariable("date")  String date) {
		return new ResponseEntity<>(movieService.findByNameAndDate(name,date), HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<Movie>> getAll() {
		return new ResponseEntity<>(movieService.findAll(), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Movie> update(@PathVariable Long id, @RequestBody Movie movie) {
		return new ResponseEntity<>(movieService.update(id, movie), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		movieService.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/test")
	public String hi() {
		log.info("hello");
		return "hello";
	}

}
