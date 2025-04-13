package com.company.ticket.service;

import java.util.List;

import com.company.ticket.model.Theatre;

public interface TheatreService {

	public Theatre save(Theatre theatre);

	public Theatre findById(Long id);

	public List<Theatre> findAll();

	public Theatre update(Long id, Theatre theatre);

	public void deleteById(Long id);

}
