package com.company.ticket.model;

import java.io.Serializable;
import java.util.Set;

import com.company.common.config.audit.Auditable;

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
@Table(name = "movie")
public class Movie extends Auditable implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4787826670036042190L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, unique = true)
	private String name;
	
	@Column(nullable = false)
	private String language;
	
	@Column(nullable = false)
	private String genres;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "movie_screening", joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "screening_id", referencedColumnName = "id"))
	private Set<Screening> screenings;
	
	
}
