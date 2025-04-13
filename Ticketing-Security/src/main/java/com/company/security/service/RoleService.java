package com.company.security.service;

import java.util.List;

import com.company.security.model.Role;

public interface RoleService {

	public Role save(Role role);

	public List<Role> findAll();

}
