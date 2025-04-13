package com.company.ticket.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.ticket.model.Screening;

public interface ScreeningRepository extends JpaRepository<Screening,Long> {

}
