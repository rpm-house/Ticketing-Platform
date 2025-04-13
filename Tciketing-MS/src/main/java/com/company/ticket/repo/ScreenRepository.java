package com.company.ticket.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.ticket.model.Screen;

public interface ScreenRepository extends JpaRepository<Screen,Long> {

}
