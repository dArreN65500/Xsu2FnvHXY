// 代码生成时间: 2025-08-28 23:20:04
import org.springframework.stereotype.Service;
import java.util.Random;

/**
 * Service class for generating random numbers.
 * This class follows Java best practices and provides
 * a clear structure for easy understanding, error handling,
 * and maintenance.
 */
@Service
public class RandomNumberGeneratorService {

    private static final Random RANDOM = new Random();

    /**
     * Generates a random integer within the specified range.
     * 
     * @param min The lower bound of the range (inclusive)
     * @param max The upper bound of the range (exclusive)
     * @return A random integer within the specified range
     * @throws IllegalArgumentException If min is greater than max
     */
    public int generateRandomNumber(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("Minimum value must be less than maximum value.");
        }
        return RANDOM.nextInt(max - min) + min;
    }

    /**
     * Generates a random double between 0.0 and 1.0.
     * 
     * @return A random double in the range [0.0, 1.0)
     */
    public double generateRandomDouble() {
        return RANDOM.nextDouble();
    }
}
