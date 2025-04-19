package com.company.ticket.model;

import java.io.Serializable;
import java.util.List;

import com.company.common.config.audit.Auditable;
import com.company.ticket.util.SeatInfoListConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
	private int blockedSeats;

	@Column(nullable = false)
	private String type;
	
	@Convert(converter = SeatInfoListConverter.class)
    @Column(columnDefinition = "TEXT", nullable = false)
    private List<SeatInfo> seatInfoList;

}
