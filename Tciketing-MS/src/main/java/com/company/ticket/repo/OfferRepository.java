package com.company.ticket.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.ticket.model.Offer;

public interface OfferRepository extends JpaRepository<Offer,Long> {

}
