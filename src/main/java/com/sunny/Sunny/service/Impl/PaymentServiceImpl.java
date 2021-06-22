package com.sunny.Sunny.service.Impl;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.sunny.Sunny.entity.Order;
import com.sunny.Sunny.model.request.PaymentRequest;
import com.sunny.Sunny.repository.OrderRepository;
import com.sunny.Sunny.repository.PaymentRepository;
import com.sunny.Sunny.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private PaymentRequest paymentRequest;

    @Autowired
    APIContext apiContext;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public Payment createPayment(
            Double total,
            String currency,
            String method,
            String intent,
            String description,
            String cancelUrl,
            String successUrl) throws PayPalRESTException {

        this.paymentRequest = new PaymentRequest(total, currency, method, intent, description);

        Amount amount = new Amount();
        amount.setCurrency(currency);
        total = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP).doubleValue();
        amount.setTotal(String.format("%.2f", total));

        Transaction transaction = new Transaction();
        transaction.setDescription(description);
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(method.toString());

        Payment payment = new Payment();
        payment.setIntent(intent);
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);

        payment.setRedirectUrls(redirectUrls);

        return payment.create(apiContext);
    }

    @Override
    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);
        System.out.println(this.paymentRequest);
        return payment.execute(apiContext, paymentExecution);
    }

    @Override
    public com.sunny.Sunny.entity.Payment insertPayment(Integer buyer_id, String paymentId, String payerId) {
        Order order = new Order();
        order.setBuyer_id(buyer_id);
        order.setTotalPrice(this.paymentRequest.getPrice());
        order = this.orderRepository.save(order);
        com.sunny.Sunny.entity.Payment payment = new com.sunny.Sunny.entity.Payment(
                buyer_id,
                this.paymentRequest.getPrice(),
                this.paymentRequest.getCurrency(),
                this.paymentRequest.getMethod(),
                this.paymentRequest.getIntent(),
                this.paymentRequest.getDescription(),
                paymentId,
                payerId,
                order
        );
        return this.paymentRepository.save(payment);
    }
}
