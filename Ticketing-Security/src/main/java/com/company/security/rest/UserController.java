package com.company.security.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.security.model.User;
import com.company.security.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody User user) {
		User savedUser = null;
		log.info("User : {}" , user);
		savedUser = userService.saveOrUpdate(user, false);
		return new ResponseEntity<>(savedUser.toString(), HttpStatus.OK);
	}
}
