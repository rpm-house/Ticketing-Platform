package com.company.dto;

import java.io.Serializable;

import com.company.common.config.Auditable;

import lombok.Data;

@Data
public class AuthResponseDTO  extends Auditable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4398194538966243062L;
	private String accessToken;
}
