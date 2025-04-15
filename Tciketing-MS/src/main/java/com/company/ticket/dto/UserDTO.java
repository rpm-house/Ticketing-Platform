package com.company.ticket.dto;

import java.io.Serializable;

import com.company.common.config.Auditable;

import lombok.Data;

@Data
public class UserDTO  extends Auditable implements Serializable{

	private String name;
	private String username;
    private String password;
    private String email;
    private String userType;
}
