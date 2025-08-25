// 代码生成时间: 2025-08-26 04:16:29
import org.springframework.stereotype.Component;
import org.springframework.http.ResponseEntity;

@Component
public class ApiResponseFormatter {
# NOTE: 重要实现细节

    /**
     * 格式化响应消息
# 改进用户体验
     *
     * @param data 响应数据
     * @param message 响应消息
     * @param status HttpStatus对应的状态码
     * @return 格式化后的响应实体
     */
    public ResponseEntity<?> formatResponse(Object data, String message, HttpStatus status) {
# TODO: 优化性能
        try {
            ApiResponse response = new ApiResponse(message, data);
            return new ResponseEntity<>(response, status);
        } catch (Exception e) {
            // 错误处理，返回内部服务器错误
# 改进用户体验
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * ApiResponse内部类，用于封装响应数据
     */
# TODO: 优化性能
    private static class ApiResponse {
# 添加错误处理
        private String message;
# 增强安全性
        private Object data;
# 改进用户体验

        public ApiResponse(String message, Object data) {
# NOTE: 重要实现细节
            this.message = message;
            this.data = data;
        }

        // getter和setter方法
        public String getMessage() {
            return message;
        }
# NOTE: 重要实现细节

        public void setMessage(String message) {
            this.message = message;
        }
# TODO: 优化性能

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }
# NOTE: 重要实现细节
}