package com.company.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class LoginDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5660124587910097014L;
	private String username;
    private String password;

    
}
