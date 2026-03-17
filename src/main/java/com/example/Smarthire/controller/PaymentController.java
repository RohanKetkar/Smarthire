package com.example.Smarthire.controller;

import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @PostMapping("/create")
    public Map<String, String> createPaymentIntent(@RequestBody Map<String, Object> data) throws Exception {

        Long amount = Long.parseLong(data.get("amount").toString());

        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount(amount)
                        .setCurrency("inr")
                        .build();

        PaymentIntent intent = PaymentIntent.create(params);

        Map<String, String> response = new HashMap<>();
        response.put("clientSecret", intent.getClientSecret());

        return response;
    }
}
