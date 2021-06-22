package com.sunny.Sunny.service;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.sunny.Sunny.model.request.PaymentRequest;

public interface PaymentService {

    Payment createPayment(
            Double total,
            String currency,
            String method,
            String intent,
            String description,
            String cancelUrl,
            String successUrl) throws PayPalRESTException;

    Payment executePayment(String paymentId, String payerId) throws PayPalRESTException;

    com.sunny.Sunny.entity.Payment insertPayment(Integer buyer_id, String paymentId, String payerId);
}
