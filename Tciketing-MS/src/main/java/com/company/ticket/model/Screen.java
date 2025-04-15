package com.company.ticket.model;

import java.io.Serializable;

import com.company.common.config.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "screen")
public class Screen extends Auditable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7814415032470817837L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private int totalSeats;

	@Column(nullable = false)
	private String type;

}
