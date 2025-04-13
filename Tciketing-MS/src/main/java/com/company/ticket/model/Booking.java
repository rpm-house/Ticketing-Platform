package com.company.ticket.model;

import com.company.security.model.User;

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
import lombok.Data;

@Entity
@Data
@Table(name = "ticket")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Transient
	@Column(nullable = false, unique = true)
	private Long screeningId;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "screening_id", referencedColumnName = "id")
	private Screening screening;

	@Column(nullable = false, unique = true)
	private int seatNo;

	@Transient
	@Column(nullable = false)
	private Long userId;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
}
