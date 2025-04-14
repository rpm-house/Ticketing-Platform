package com.company.ticket.service.impl;

import java.util.Base64;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.company.ticket.service.PaymentService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Value("${razorpay.key}")
	private String razorpayKey;

	@Value("${razorpay.secret}")
	private String razorpaySecret;

	public Order createOrder(int amount) throws RazorpayException {
		RazorpayClient client = new RazorpayClient(razorpayKey, razorpaySecret);

		JSONObject orderRequest = new JSONObject();
		orderRequest.put("amount", amount * 100);
		orderRequest.put("currency", "INR");
		orderRequest.put("receipt", "txn_" + UUID.randomUUID());
		orderRequest.put("payment_capture", 1);

		Order order = client.orders.create(orderRequest);
		return order;
	}
	
	@Override
	public boolean verifyPaymentSignature(String orderId, String paymentId, String signature) {
	    try {
	        String payload = orderId + "|" + paymentId;
	        String actualSignature = hmacSHA256(payload, razorpaySecret);

	        return actualSignature.equals(signature);
	    } catch (Exception e) {
	        return false;
	    }
	}

	private String hmacSHA256(String data, String key) throws Exception {
	    Mac sha256Hmac = Mac.getInstance("HmacSHA256");
	    SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "HmacSHA256");
	    sha256Hmac.init(secretKey);
	    byte[] hash = sha256Hmac.doFinal(data.getBytes());
	    return Base64.getEncoder().encodeToString(hash);
	}


}
