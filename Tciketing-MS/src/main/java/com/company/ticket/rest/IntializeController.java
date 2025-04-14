package com.company.ticket.rest;

import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.security.model.Role;
import com.company.security.model.User;
import com.company.security.service.RoleService;
import com.company.security.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/initialize")
public class IntializeController {

	@Autowired
	RoleService roleService;

	@Autowired
	UserService userService;

	@Operation(summary = "Create Admin Role and User to Initialize", description = "Initialization")
	@PostMapping("/")
	public String initialize() throws URISyntaxException {

		Role role = new Role();
		role.setName("ROLE_ADMIN");
		Role savedRole = roleService.save(role);
		Set<Role> roleSet = new HashSet<Role>();
		roleSet.add(savedRole);

		User user = new User();
		user.setEmail("admin@gmail.com");
		user.setName("admin");
		user.setUsername("admin");
		user.setPassword("admin123");
		user.setRoles(roleSet);

		userService.saveOrUpdate(user, false);
		return "Initialization Completed";
	}

}
