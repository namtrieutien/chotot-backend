package com.sunny.Sunny.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.sunny.Sunny.exception.PaypalApiException;
import com.sunny.Sunny.model.request.PaymentRequest;
import com.sunny.Sunny.service.Impl.UserDetailsImpl;
import com.sunny.Sunny.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @Value("${email.baseUrl}")
    private String baseUrl;

    private final String CANCEL_URL = "/cancel";
    private final String SUCCESS_URL = "/success";

    @PostMapping("/pay")
    public ResponseEntity<?> pay(@Valid @RequestBody PaymentRequest paymentRequest) {

        try {
            Payment payment = this.paymentService.createPayment(
                    paymentRequest.getPrice(),
                    paymentRequest.getCurrency(),
                    paymentRequest.getMethod(), //paypal, credit_card, visa ?
                    paymentRequest.getIntent(),
                    paymentRequest.getDescription(),
                    baseUrl + CANCEL_URL,
                    baseUrl + SUCCESS_URL
            );
            Map<Object, Object> response = new HashMap<>();
            response.put("links",payment.getLinks());
            for(Links link:payment.getLinks()) {
                if(link.getRel().equals("approval_url")) {
                    response.put("payment_link",link.getHref());
                }
            }
            return ResponseEntity.ok(response);

        }  catch (PayPalRESTException e) {
            throw new PaypalApiException(e.getDetails().toString());
        }
    }

    @GetMapping("/success")
    public ResponseEntity<?> success(@AuthenticationPrincipal UserDetailsImpl details,
                                     @RequestParam("paymentId") String paymentId,
                                     @RequestParam("PayerID") String payerId) {
        Map<Object, Object> response = new HashMap<>();

        try {
            Payment payment = this.paymentService.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {
                com.sunny.Sunny.entity.Payment payment1 =
                        this.paymentService.insertPayment(details.getUserDTO().getId(), paymentId, payerId);
                response.put("status", true);
                response.put("message", "Payment Success");
                response.put("paymentInfo", payment1);
                return ResponseEntity.ok(response);
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        response.put("status", false);
        response.put("message", "Payment Failed");
        return ResponseEntity.ok(response);
    }
}
