package com.company.ticket.rest;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.security.model.Role;
import com.company.security.model.User;
import com.company.security.service.RoleService;
import com.company.security.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

	@Autowired
	RoleService roleService;

	@Autowired
	UserService userService;

	@PostMapping("/saveRole")
	public ResponseEntity<Role> createRole(@RequestBody Role role) throws URISyntaxException {
		Role savedRole = roleService.save(role);
		return new ResponseEntity<>(savedRole, HttpStatus.OK);
	}

	@GetMapping("/roles")
	public ResponseEntity<List<Role>> getRoles() {
		List<Role> roles = roleService.findAll();
		return new ResponseEntity<>(roles, HttpStatus.OK);
	}

	@PostMapping("/saveUser")
	public ResponseEntity<User> save(@RequestBody User user) {
		User savedUser = null;
		log.info("User : {}", user);
		savedUser = userService.saveOrUpdate(user, false);
		return new ResponseEntity<>(savedUser, HttpStatus.OK);
	}

}
