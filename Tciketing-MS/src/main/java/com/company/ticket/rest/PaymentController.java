package com.company.ticket.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.ticket.service.PaymentService;
import com.razorpay.Order;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	PaymentService paymentService;

	@Operation(summary = "Create Payment", description = "Payment Creation.")
	@PostMapping("/create/{amount}")
	public ResponseEntity<String> createOrder(@PathVariable("amount") int amount) {
		try {
			Order order = paymentService.createOrder(amount);
			log.info("Payment Done.. {}", HttpStatus.OK);
			return ResponseEntity.ok(order.toJson().toString());
		} catch (Exception e) {
			log.error("Payment Failure.. {}", HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<>("Payment Failure..", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Operation(summary = "Verify Payment", description = "Payment Verification.")
	@PostMapping("/verify/{orderId}/{paymentId}/{signature}")
	public ResponseEntity<Boolean> verifyPaymentSignature(@PathVariable("orderId") String orderId, @PathVariable("paymentId") String paymentId,
			 @PathVariable("signature") String signature) {
		boolean isVerified = false;
		try {
			isVerified = paymentService.verifyPaymentSignature(orderId, paymentId, signature);
			log.info("Payment Verification Done.. {}", HttpStatus.OK);
			return ResponseEntity.ok(isVerified);
		} catch (Exception e) {
			log.error("Payment Verification Failed.. {}", HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<>(isVerified, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
