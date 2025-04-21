package com.company.ticket.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.ticket.dto.OfferRequestDTO;
import com.company.ticket.dto.OfferResponseDTO;
import com.company.ticket.model.Offer;
import com.company.ticket.model.Screening;
import com.company.ticket.repo.OfferRepository;
import com.company.ticket.service.OfferService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OfferServiceImpl implements OfferService {

	@Autowired
	OfferRepository offerRepository;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public OfferResponseDTO save(OfferRequestDTO offerRequestDTO) {
		Offer offer = new Offer();
		offer.setName(offerRequestDTO.getName());
		offer.setValue(offerRequestDTO.getValue());
		offer.setScreening(new Screening(offerRequestDTO.getScreeningId()));
		Offer saved = offerRepository.save(offer);
		OfferResponseDTO offerResponseDTO = new OfferResponseDTO();
		BeanUtils.copyProperties(saved, offerResponseDTO);
		offerResponseDTO.setOfferId(saved.getId());
		return offerResponseDTO;
	}

	@Override
	public Offer findById(Long id) {/*
									 * Offer offer =offerRepository.findById(id).get(); log.info("offer : "+offer);
									 * OfferResponseDTO offerResponseDTO = new OfferResponseDTO();
									 * BeanUtils.copyProperties(offer, offerResponseDTO);
									 * log.info("offerResponseDTO : "+offerResponseDTO);
									 */
		return offerRepository.findById(id).get();
	}

	@Override
	public List<Offer> findAll() {
		return offerRepository.findAll();
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public OfferResponseDTO update(Long id, OfferRequestDTO offerRequestDTO) {
		Offer existingOffer = offerRepository.findById(id).get();
		existingOffer.setName(offerRequestDTO.getName());
		existingOffer.setValue(offerRequestDTO.getValue());
		existingOffer.setScreening(new Screening(offerRequestDTO.getScreeningId()));
		Offer saved = offerRepository.save(existingOffer);
		OfferResponseDTO offerResponseDTO = new OfferResponseDTO();
		BeanUtils.copyProperties(saved, offerResponseDTO);
		offerResponseDTO.setOfferId(saved.getId());
		return offerResponseDTO;
	}

	@Override
	public void deleteById(Long id) {
		offerRepository.deleteById(id);
	}

}
