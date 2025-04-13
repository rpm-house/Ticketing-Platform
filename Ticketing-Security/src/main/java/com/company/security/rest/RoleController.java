package com.company.security.rest;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.security.model.Role;
import com.company.security.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	RoleService roleService;

	@PostMapping("/save")
	public ResponseEntity<Role> createRole(@RequestBody Role role) throws URISyntaxException {
		Role savedRole = roleService.save(role);
		return new ResponseEntity<>(savedRole, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<String> getRoles() {
		return new ResponseEntity<>(roleService.findAll().toString(), HttpStatus.OK);
	}
}
