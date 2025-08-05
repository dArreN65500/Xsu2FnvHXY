// 代码生成时间: 2025-08-05 08:03:24
package com.example.security;

import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

@Service
public class XssProtectionService {
    
    /**
     * Escapes HTML content to prevent XSS attacks.
     * @param input The input string that may contain XSS.
     * @return The escaped string safe to display in a web browser.
     */
    public String escapeXss(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        try {
            // HtmlUtils provides a convenient way to escape dangerous characters
            // in HTML content to prevent XSS.
            return HtmlUtils.htmlEscape(input);
        } catch (Exception e) {
            // Log the error and return an empty string to prevent potential XSS
            // In a real-world scenario, you would want to handle this more gracefully
            // e.g., logging the error and returning a user-friendly error message.
            return "";
        }
    }
    
    // Additional methods for other sanitization or security checks can be added here.
}
