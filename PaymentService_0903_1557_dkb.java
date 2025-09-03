// 代码生成时间: 2025-09-03 15:57:56
package com.example.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentService {

    private final RestTemplate restTemplate;
    private final PaymentRepository paymentRepository;

    // Constructor injection of RestTemplate and PaymentRepository
    @Autowired
    public PaymentService(RestTemplate restTemplate, PaymentRepository paymentRepository) {
        this.restTemplate = restTemplate;
        this.paymentRepository = paymentRepository;
    }

    /**
     * Process the payment for a given order ID.
     *
     * @param orderId The ID of the order for which the payment is to be processed.
     * @return ResponseEntity containing the payment status and message.
     */
    public ResponseEntity<String> processPayment(Long orderId) {
        try {
            // Check if the order exists
            Order order = paymentRepository.findOrderById(orderId);
            if (order == null) {
                return ResponseEntity.badRequest().body("Order not found for ID: " + orderId);
            }

            // Simulate payment processing (this should be replaced with actual payment processing logic)
            String paymentUrl = "http://payment-gateway/api/pay";
            Map<String, Object> paymentDetails = new HashMap<>();
            paymentDetails.put("orderId", orderId);
            paymentDetails.put("amount", order.getAmount());
            ResponseEntity<String> paymentStatus = restTemplate.postForEntity(paymentUrl, paymentDetails, String.class);

            // Check if payment was successful
            if (paymentStatus.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.ok("Payment processed successfully for order ID: " + orderId);
            } else {
                return ResponseEntity.status(paymentStatus.getStatusCode()).body(
                        "Payment failed for order ID: " + orderId + ", Message: " + paymentStatus.getBody());
            }
        } catch (Exception e) {
            // Log the exception and return an error response
            // Log the exception (logging framework should be configured)
            // e.g., logger.error("Error processing payment", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing payment: " + e.getMessage());
        }
    }
}
