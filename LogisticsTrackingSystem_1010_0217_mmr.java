// 代码生成时间: 2025-10-10 02:17:28
package com.example.logistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class LogisticsTrackingSystem {
    public static void main(String[] args) {
        SpringApplication.run(LogisticsTrackingSystem.class, args);
    }
}

// Service Interface for Logistics Tracking
interface TrackingService {
    @GetMapping("/track/{orderId}")
    String trackOrder(@PathVariable String orderId);
}

// Feign Client for Calling Tracking Service
@FeignClient(name = "tracking-service", url = "http://localhost:8081")
interface TrackingClient extends TrackingService {
}

// REST Controller for Handling Requests
@RestController
public class TrackingController {

    private final TrackingClient trackingClient;

    public TrackingController(TrackingClient trackingClient) {
        this.trackingClient = trackingClient;
    }

    // Endpoint to Track Order
    @GetMapping("/api/track/{orderId}")
    public String trackOrder(@PathVariable String orderId) {
        try {
            // Delegate the call to the Tracking Service
            return trackingClient.trackOrder(orderId);
        } catch (Exception e) {
            // Handle any exceptions and return an error message
            return "Error tracking order: " + e.getMessage();
        }
    }
}
