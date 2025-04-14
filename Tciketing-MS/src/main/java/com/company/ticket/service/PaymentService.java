package com.company.ticket.service;

import com.razorpay.Order;
import com.razorpay.RazorpayException;

public interface PaymentService {

	public Order createOrder(int amount) throws RazorpayException;

	boolean verifyPaymentSignature(String orderId, String paymentId, String signature);

}
