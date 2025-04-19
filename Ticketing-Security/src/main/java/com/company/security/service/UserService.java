package com.company.security.service;

import java.util.List;

import com.company.security.model.User;

public interface UserService {

	public User saveOrUpdate(User user, boolean b);

	public List<User> findAll();

}
