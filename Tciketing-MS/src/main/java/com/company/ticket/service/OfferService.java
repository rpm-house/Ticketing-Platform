package com.company.ticket.service;

import java.util.List;

import com.company.ticket.dto.OfferRequestDTO;
import com.company.ticket.dto.OfferResponseDTO;
import com.company.ticket.model.Offer;

public interface OfferService {

	public OfferResponseDTO save(OfferRequestDTO offerRequestDTO);

	public Offer findById(Long id);

	public List<Offer> findAll();

	public OfferResponseDTO update(Long id, OfferRequestDTO offerRequestDTO);

	public void deleteById(Long id);

	


}
