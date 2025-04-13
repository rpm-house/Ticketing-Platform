package com.company.ticket.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Data
@Table(name = "seat", uniqueConstraints=
@UniqueConstraint(columnNames={"row_No", "seat_No"}))
public class Seat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Transient
	@Column(nullable = false, unique = true)
	private Long screeningId;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "screening_id", referencedColumnName = "id")
	private Screening screening;
	
	@Column(nullable = false)
	private int totalSeats;
	
	@Column(nullable = false)
	private int availableSeats;
	
}
