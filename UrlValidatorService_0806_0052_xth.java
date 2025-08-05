// 代码生成时间: 2025-08-06 00:52:20
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Service class to validate the URL link.
 */
@Service
public class UrlValidatorService {

    private final RestTemplate restTemplate;

    public UrlValidatorService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Validate if the given URL is valid and accessible.
     *
     * @param url The URL to be validated.
     * @return true if the URL is valid and accessible, false otherwise.
     */
    public boolean isValidUrl(String url) {
        try {
            // Check if the URL is a valid URI
            new URI(url).toURL();

            // Make a HEAD request to check if the URL is accessible
            ResponseEntity<String> response = restTemplate.headForHeaders(url);

            // Return true if the response status is OK (200)
            return response.getStatusCode().is2xxSuccessful();
        } catch (URISyntaxException e) {
            // Invalid URI format
            return false;
        } catch (Exception e) {
            // Handle other exceptions, such as connectivity issues
            return false;
        }
    }
}
