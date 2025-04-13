package com.company.security.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.company.security.model.User;
import com.company.security.repo.UserRepository;
import com.company.security.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	@Override
	public User saveOrUpdate(User user, boolean isUpdate) {

		User savedUser = null;
		if (isUpdate) {
			User currentUser = null;

			currentUser = userRepository.findById(user.getId()).orElse(new User());
			BeanUtils.copyProperties(user, currentUser, "userName", "password");
			savedUser = userRepository.save(currentUser);

		} else {
			user.setPassword(bcryptEncoder.encode(user.getPassword()));
			savedUser = userRepository.save(user);

		}

		return savedUser;
	}

}
