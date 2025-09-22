// 代码生成时间: 2025-09-23 00:41:17
package com.example.randomnumbergenerator;

import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Service class to generate random numbers.
 */
@Service
public class RandomNumberGeneratorService {

    private static final Random random = new Random();

    /**
     * Generates a random integer between 0 (inclusive) and the specified value (exclusive).
     *
     * @param bound The upper bound (exclusive) of the random number.
     * @return A random integer between 0 (inclusive) and the specified value (exclusive).
     * @throws IllegalArgumentException If the bound is less than 0.
     */
    public int generateRandomNumber(int bound) {
        if (bound < 0) {
            throw new IllegalArgumentException("The bound must be greater than 0");
        }

        // Ensure the bound is positive to avoid infinite loop
        bound = Math.abs(bound);

        return random.nextInt(bound);
    }
}
