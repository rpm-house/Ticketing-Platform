package com.company.ticket.model;

import java.io.Serializable;

import com.company.common.config.audit.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "offer", uniqueConstraints = { @UniqueConstraint(columnNames = { "screening_id", "name" }) 
})
public class Offer extends Auditable implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 5054357527641986906L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String value;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "screening_id", nullable = false)
	private Screening screening;

}
