// 代码生成时间: 2025-09-08 00:00:43
package com.example.demo.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * ApiResponseFormatter is a utility class for formatting API responses.
 * It provides methods to create standardized success and error responses.
 */
public class ApiResponseFormatter {

    /**
     * Returns a standardized success response.
     *
     * @param data The data to be returned in the response.
     * @param <T> The type of the data.
     * @return A ResponseEntity with the success status and the data.
     */
    public static <T> ResponseEntity<Map<String, Object>> successResponse(T data) {
        Map<String, Object> response = new HashMap<>();
        response.put("data", data);
        response.put("status", "success");
        response.put("message", "Request processed successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Returns a standardized error response.
     *
     * @param errorCode The code representing the error.
     * @param errorMessage The message describing the error.
     * @return A ResponseEntity with the bad request status and the error details.
     */
    public static ResponseEntity<Map<String, Object>> errorResponse(String errorCode, String errorMessage) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("code", errorCode);
        response.put("message", errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // Additional methods for different types of responses can be added here.
}