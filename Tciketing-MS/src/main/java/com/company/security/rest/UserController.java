package com.company.security.rest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.common.exception.TicketException;
import com.company.security.model.Role;
import com.company.security.model.User;
import com.company.security.service.RoleService;
import com.company.security.service.UserService;
import com.company.ticket.dto.UserDTO;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	@Operation(summary = "Save user", description = "Returns User Object.")
	@PostMapping("/save")
	public ResponseEntity<User> save(@RequestBody UserDTO userDTO) throws TicketException {
		User savedUser = null;
		log.info("User : {}", userDTO);
		User user = new User();
		user.setEmail(userDTO.getEmail());
		user.setName(userDTO.getName());
		user.setUsername(userDTO.getUsername());
		user.setPassword(userDTO.getPassword());
		List<Role> roles = roleService.findAll();
		Set<Role> roleSet = new HashSet<Role>();
		Role role;
		if (userDTO.getUserType().equalsIgnoreCase("B2B")) {
			role = roles.stream().filter(r -> r.getName().equalsIgnoreCase("ROLE_PARTNER")).findFirst().get();
		} else if (userDTO.getUserType().equalsIgnoreCase("B2C")) {
			role = roles.stream().filter(r -> r.getName().equalsIgnoreCase("ROLE_CUSTOMER")).findFirst().get();
		} else {
			log.error("In valid User Type.");
			throw new TicketException("In valid User Type.");
		}
		roleSet.add(role);
		user.setRoles(roleSet);
		savedUser = userService.saveOrUpdate(user, false);
		log.info("User Created. : {}", savedUser);
		return new ResponseEntity<>(savedUser, HttpStatus.OK);
	}

}
