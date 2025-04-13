package com.company.security.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.security.model.User;

public interface UserRepository extends JpaRepository<User,Long> {

	Optional<User> findByUsername(String username);
	
}
