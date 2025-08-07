// 代码生成时间: 2025-08-07 11:25:21
package com.example;

import org.springframework.stereotype.Service;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class RandomNumberGeneratorService {

    private static final Logger logger = LoggerFactory.getLogger(RandomNumberGeneratorService.class);
    private final Random random;

    // Constructor
    public RandomNumberGeneratorService() {
        this.random = new Random();
    }

    /**
     * Generates a random number within the specified range.
     *
     * @param min The minimum value (inclusive)
     * @param max The maximum value (exclusive)
     * @return A random integer between min and max
     */
    public int generateRandomNumber(int min, int max) {
        if (min >= max) {
            logger.error("Invalid range: min must be less than max");
            throw new IllegalArgumentException("min must be less than max");
        }

        return random.nextInt(max - min) + min;
    }

    /**
     * Generates a random number with a default range of 0 to 100.
     *
     * @return A random integer between 0 and 100
     */
    public int generateRandomNumber() {
        return generateRandomNumber(0, 100);
    }
}
