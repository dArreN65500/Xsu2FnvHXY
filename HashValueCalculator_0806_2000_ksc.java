// 代码生成时间: 2025-08-06 20:00:17
// HashValueCalculator.java

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

@Service
public class HashValueCalculator {

    public String calculateHash(String input) {
        // Check if the input is null or empty
        if (StringUtils.isEmpty(input)) {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }

        try {
            // Use MD5 algorithm to calculate hash
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(input.getBytes(StandardCharsets.UTF_8));
            byte[] hashBytes = messageDigest.digest();

            // Convert the byte array to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0').append(hex);
                } else {
                    hexString.append(hex);
                }
            }

            // Return the calculated hash value
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Handle the exception if the algorithm is not available
            throw new RuntimeException("MD5 algorithm not found", e);
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        HashValueCalculator calculator = new HashValueCalculator();

        // Test the hash calculation with a sample input
        String input = "Hello, World!";
        String hashValue = calculator.calculateHash(input);
        System.out.println("Hash value of 'Hello, World!' is: " + hashValue);
    }
}