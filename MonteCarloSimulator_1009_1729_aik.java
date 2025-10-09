// 代码生成时间: 2025-10-09 17:29:55
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
@RestController
public class MonteCarloSimulator {

    @GetMapping("/simulate")
    public String simulatePi(@RequestParam("iterations") int iterations) {
        try {
            // Validate the number of iterations
            if (iterations <= 0) {
                throw new IllegalArgumentException("Number of iterations must be positive");
            }

            // Initialize the count of points inside the circle
            int inside = 0;

            // Perform the specified number of iterations
            for (int i = 0; i < iterations; i++) {
                // Generate random coordinates for a point within the unit square
                double x = ThreadLocalRandom.current().nextDouble(-1, 1);
                double y = ThreadLocalRandom.current().nextDouble(-1, 1);

                // Check if the point is inside the unit circle (x^2 + y^2 <= 1)
                if (Math.pow(x, 2) + Math.pow(y, 2) <= 1) {
                    inside++;
                }
            }

            // Estimate the value of Pi using the Monte Carlo method
            double piEstimate = 4 * ((double) inside / iterations);

            // Return the estimated value of Pi as a string
            return "Estimated value of Pi: " + piEstimate;
        } catch (IllegalArgumentException e) {
            // Handle the error and return a meaningful message
            return "Error: " + e.getMessage();
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(MonteCarloSimulator.class, args);
    }
}
