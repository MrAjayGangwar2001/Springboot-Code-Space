package com.OTP_Login.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
// @CrossOrigin(origins = "*")
@RequestMapping("/api/payment")
public class PaymentController {

        public PaymentController(@Value("${stripe.secret-key}") String stripeSecretKey) {
                Stripe.apiKey = stripeSecretKey;
        }

        @PostMapping("/create")
        public Map<String, String> createSession(@RequestBody Map<String, Object> data) throws Exception {

                int amount = (int) data.get("amount");

                // 🔒 validation
                if (amount < 10) {
                        throw new RuntimeException("Minimum amount is ₹10");
                }

                SessionCreateParams params = SessionCreateParams.builder()
                                .setMode(SessionCreateParams.Mode.PAYMENT)
                                .setCustomerEmail((String) data.get("email"))
//
                                .setSuccessUrl("http://localhost:5173/payment-success?session_id={CHECKOUT_SESSION_ID}")
                                .setCancelUrl("http://localhost:5173/payment-cancel")
                                .addLineItem(
                                                SessionCreateParams.LineItem.builder()
                                                                .setQuantity(1L)
                                                                .setPriceData(
                                                                                SessionCreateParams.LineItem.PriceData
                                                                                                .builder()
                                                                                                .setCurrency("inr")
                                                                                                // .setUnitAmount(50000L)
                                                                                                // // fixed Rs 500
                                                                                                .setUnitAmount((long) amount
                                                                                                                * 100) // Custom
                                                                                                                       // Amount
                                                                                                .setProductData(
                                                                                                                SessionCreateParams.LineItem.PriceData.ProductData
                                                                                                                                .builder()
                                                                                                                                // .setName("Premium
                                                                                                                                // Plan")
                                                                                                                                // //
                                                                                                                                // fixed
                                                                                                                                // plan
                                                                                                                                .setName("Total Purchase Amount")
                                                                                                                                .build())
                                                                                                .build())
                                                                .build())
                                .build();

                Session session = Session.create(params);

                // 🔥 return URL instead of sessionId
                return Map.of("url", session.getUrl());
        }

        @GetMapping("/session/{id}")
        public ResponseEntity<?> getSession(@PathVariable String id) {
                try {
                        // Stripe.apiKey = stripeSecretKey;

                        Session session = Session.retrieve(
                                        id,
                                        Map.of("expand", List.of("payment_intent")),
                                        null);

                        PaymentIntent pi = PaymentIntent.retrieve(session.getPaymentIntent());

                        return ResponseEntity.ok(
                                        Map.of(
                                                        "amount", pi.getAmount(),
                                                        "email", session.getCustomerDetails().getEmail(),
                                                        "paymentId", pi.getId(),
                                                        "currency", pi.getCurrency(),
                                                        "status", pi.getStatus()));

                } catch (Exception e) {
                        e.printStackTrace();
                        return ResponseEntity.badRequest().body(
                                        Map.of("error", e.getMessage()));
                }
        }

//    @PostMapping("/payment-success")
//    public ResponseEntity<?> paymentSuccess(@RequestBody PaymentDto dto) {
//        emailService.sendPaymentSuccessMail(
//                dto.getEmail(),
//                dto.getAmount(),
//                dto.getPaymentId()
//        );
//        return ResponseEntity.ok("Mail sent");
//    }


};
