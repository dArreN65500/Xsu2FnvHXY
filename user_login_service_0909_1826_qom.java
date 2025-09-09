// 代码生成时间: 2025-09-09 18:26:54
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
public class UserLoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String login(@RequestBody UserCredentials credentials) {
        try {
            // 验证用户凭证
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    credentials.getUsername(),
                    credentials.getPassword()
                )
            );

            // 如果验证成功，保存认证信息到安全上下文
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // 返回成功消息
            return "User logged in successfully";
        } catch (Exception ex) {
            // 错误处理
            return "Login failed: " + ex.getMessage();
        }
    }

    // 用户凭证类
    public static class UserCredentials {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}

/*
 * 说明：
 * 这个类提供了一个登录接口，接受用户名和密码，通过Spring Security的
 * AuthenticationManager进行用户认证。如果认证成功，返回登录成功消息，
 * 否则返回登录失败的原因。
 *
 * 注意：
 * 这个例子简化了错误处理和用户凭证的验证过程。在实际应用中，
 * 你可能需要添加更多的安全措施，如限制登录尝试次数，防止暴力破解。
 */