package com.company.ticket.model;

import java.io.Serializable;
import java.util.Set;

import com.company.common.config.Auditable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "theatre")
public class Theatre extends Auditable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2986802089241127443L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String city;
	@Column(nullable = false)
	private String area;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "theatre_screen", joinColumns = @JoinColumn(name = "theatre_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "screen_id", referencedColumnName = "id"))
	private Set<Screen> screens;
}
