package com.company.ticket.model;

import java.util.Set;

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
public class Theatre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String city;
	@Column(nullable = false)
	private String area;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "theatre_screen", joinColumns = @JoinColumn(name = "theatre_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "screen_id", referencedColumnName = "id"))
	private Set<Screen> screens;
}
