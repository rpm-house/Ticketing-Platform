package com.company.ticket.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserDTO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4066181567066777204L;
	private String name;
	private String username;
    private String password;
    private String email;
    private String userType;
}
