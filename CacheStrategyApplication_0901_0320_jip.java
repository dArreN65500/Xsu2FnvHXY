// 代码生成时间: 2025-09-01 03:20:33
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableCaching
public class CacheStrategyApplication {

    public static void main(String[] args) {
        SpringApplication.run(CacheStrategyApplication.class, args);
    }

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("myCache");
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(".");
            sb.append(method.getName());
            for (Object param : params) {
                sb.append("_");
                sb.append(param.toString());
            }
            return sb.toString();
        };
    }
}

@Configuration
public class CacheConfig extends CachingConfigurerSupport {

    @Override
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("myCache");
    }

    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(".");
            sb.append(method.getName());
            for (Object param : params) {
                sb.append("_");
                sb.append(param.toString());
            }
            return sb.toString();
        };
    }
}

public interface CacheService {
    String getCachedValue(String key);
    void setCachedValue(String key, String value);
}

@Service
public class CacheServiceImpl implements CacheService {

    private final CacheManager cacheManager;

    public CacheServiceImpl(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    @Cacheable(value = "myCache", key = "'get' + #key")
    public String getCachedValue(String key) {
        return "Value not found in cache";
    }

    @Override
    @CacheEvict(value = "myCache", key = "'set' + #key")
    public void setCachedValue(String key, String value) {
        // Simulate a database operation
        System.out.println("Value set in cache: " + value);
    }
}
