// 代码生成时间: 2025-08-13 18:00:02
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
# NOTE: 重要实现细节
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Service responsible for handling XSS protection.
 */
@Service
# 添加错误处理
public class XssProtectionService {

    private static final String CONTENT_TYPE = "text/html;charset=UTF-8";

    /**
     * Cleans the input to prevent XSS attacks.
     *
     * @param input The input string to be cleaned.
# 增强安全性
     * @return The cleaned input string.
     */
# 改进用户体验
    public String cleanXss(String input) {
        if (input != null) {
            // Escapes HTML characters to prevent XSS attacks.
            return HtmlUtils.htmlEscape(input);
        }
        return input;
    }
# FIXME: 处理边界情况

    /**
     * Sets the X-Content-Type-Options header to 'nosniff' to prevent MIME-sniffing attacks.
     *
     * @param request The HttpServletRequest.
     */
    public void setXContentTypeOptionsHeader(HttpServletRequest request) {
# FIXME: 处理边界情况
        request.setHeader("X-Content-Type-Options", "nosniff");
    }

    /**
     * Sets the X-XSS-Protection header to '1; mode=block' to enable XSS filtering.
# 改进用户体验
     *
     * @param request The HttpServletRequest.
     */
    public void setXXSSProtectionHeader(HttpServletRequest request) {
        request.setHeader("X-XSS-Protection", "1; mode=block");
# NOTE: 重要实现细节
    }

    /**
     * Sets the Content-Security-Policy header to restrict resources to trusted sources.
     *
     * @param request The HttpServletRequest.
# 扩展功能模块
     * @param policy  A string representing the Content Security Policy.
     */
    public void setContentSecurityPolicyHeader(HttpServletRequest request, String policy) {
# NOTE: 重要实现细节
        request.setHeader("Content-Security-Policy", policy);
# 优化算法效率
    }

    /**
     * Writes a response to the client with the proper HTTP headers and body.
     *
     * @param request  The HttpServletRequest.
     * @param response The response body to be sent to the client.
     * @throws IOException If an I/O error occurs while writing to the client.
     */
    public void writeResponse(HttpServletRequest request, String response) throws IOException {
# 优化算法效率
        request.setContentType(CONTENT_TYPE);
        setXContentTypeOptionsHeader(request);
        setXXSSProtectionHeader(request);
        setContentSecurityPolicyHeader(request, "default-src 'self'; script-src 'self' 'unsafe-inline'; img-src 'self';");
# NOTE: 重要实现细节

        try (PrintWriter out = request.getWriter()) {
            out.println(response);
        }
    }
}
