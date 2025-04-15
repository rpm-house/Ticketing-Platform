package com.company.dto;

import java.io.Serializable;

import com.company.common.config.Auditable;

import lombok.Data;

@Data
public class LoginDTO  extends Auditable implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5660124587910097014L;
	private String username;
    private String password;

    
}
