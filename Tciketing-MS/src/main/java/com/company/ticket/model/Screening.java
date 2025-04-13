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
@Table(name = "screening", uniqueConstraints=
@UniqueConstraint(columnNames={"theatre_id", "screen_id"}))

public class Screening {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Transient
	@Column(nullable = false)
	private Long theatreId;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "theatre_id", referencedColumnName = "id")
    private Theatre theatre;

	@Column(nullable = false)
	private Long screenId;

	@Column(nullable = false)
	private String screenName;

	@Column(nullable = false)
	private String screeningDate;

	@Column(nullable = false)
	private String screeningTime;

	@Column(nullable = false)
	private int availableTickets;

}
