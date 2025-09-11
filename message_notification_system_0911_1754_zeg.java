// 代码生成时间: 2025-09-11 17:54:21
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.Random;

// 主应用程序类，用于启动Spring Boot应用
@SpringBootApplication
public class MessageNotificationSystem {

    // 启动Spring Boot应用
    public static void main(String[] args) {
        SpringApplication.run(MessageNotificationSystem.class, args);
    }
}

// 控制器类，处理HTTP请求
@RestController
class NotificationController {

    // 发送通知消息的方法
    @GetMapping("/sendNotification")
    public Mono<String> sendNotification(@RequestParam String message) {
        // 模拟异步消息发送
        return notificationService.sendNotification(message)
                .retryWhen(Retry.backoff(3, Duration.ofSeconds(1)).maxBackoff(Duration.ofSeconds(5)));
    }
}

// 服务类，封装消息发送逻辑
class NotificationService {

    // 发送通知消息，模拟异步操作
    public Mono<String> sendNotification(String message) {
        // 模拟消息发送可能失败的情况
        Random random = new Random();
        if (random.nextInt(10) > 8) {
            return Mono.error(new RuntimeException("Failed to send notification"));
        } else {
            // 模拟消息发送成功的延时
            return Mono.just("Notification sent successfully").delaySubscription(Duration.ofSeconds(1));
        }
    }
}
