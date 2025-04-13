package com.company.security.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.security.model.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {

}
