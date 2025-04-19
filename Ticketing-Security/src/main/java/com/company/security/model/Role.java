package com.company.security.model;

import java.io.Serializable;

import com.company.common.config.audit.Auditable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "roles")
public class Role  extends Auditable implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7555264454648022525L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
