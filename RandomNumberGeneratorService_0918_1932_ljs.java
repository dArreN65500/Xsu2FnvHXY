// 代码生成时间: 2025-09-18 19:32:07
package com.example.randomservice;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomNumberGeneratorService {

    /**
     * Generates a random number within the specified range.
     *
     * @param min The minimum value of the range (inclusive).
     * @param max The maximum value of the range (exclusive).
     * @return A random number within the specified range.
     * @throws IllegalArgumentException If the minimum value is greater than the maximum.
     */
    public int generateRandomNumber(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("Minimum value must be less than maximum value.");
        }

        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
