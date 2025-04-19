package com.company.common.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SecurityUtils {

	public static String convertObjectToJson(ErrorResponse object) throws JsonProcessingException {
		if (object == null) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}
}
