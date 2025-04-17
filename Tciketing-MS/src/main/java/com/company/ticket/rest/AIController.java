package com.company.ticket.rest;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ai")
public class AIController {

	 private final RestTemplate restTemplate = new RestTemplate();

	    @PostMapping("/predict")
	    public ResponseEntity<String> getPrediction(@RequestBody Map<String, String> payload) {
	        String url = "http://ml-model:5000/predict";
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<Map<String, String>> request = new HttpEntity<>(payload, headers);
	        return restTemplate.postForEntity(url, request, String.class);
	    }

}
