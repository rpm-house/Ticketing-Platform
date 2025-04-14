package com.company.ticket.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.ticket.service.CurrencyService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/currency")
public class CurrencyController {

	@Autowired
	CurrencyService currencyService;

	@Operation(summary = "Convert Currency", description = "Currency Convertion.")
	@GetMapping("/convert")
	public ResponseEntity<String> convert(@RequestParam String from, @RequestParam String to,
			@RequestParam double amount) {
		Double result = currencyService.convertCurrency(from, to, amount);
		log.info("Converted Value : {} ", result);

		return ResponseEntity.ok(String.format("%.2f %s = %.2f %s", amount, from, result, to));
	}

}
