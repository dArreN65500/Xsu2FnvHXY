// 代码生成时间: 2025-09-09 04:10:54
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

@SpringBootApplication
@RestController
public class PasswordEncryptionDecryptionApp {

    // 定义AES加密算法
    private static final String ALGORITHM = "AES";

    public static void main(String[] args) {
        SpringApplication.run(PasswordEncryptionDecryptionApp.class, args);
    }

    // POST请求接口，用于加密密码
    @PostMapping("/encrypt")
    public String encryptPassword(@RequestParam String password) {
        try {
            // 生成密钥
            SecretKey secretKey = KeyGenerator.getInstance(ALGORITHM).generateKey();
            // 转换密钥
            byte[] keyBytes = secretKey.getEncoded();
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, ALGORITHM);

            // 获取Cipher实例
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            // 初始化Cipher为加密模式
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            // 加密密码
            byte[] encryptedBytes = cipher.doFinal(password.getBytes());
            // 返回Base64编码的加密字符串
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            // 错误处理
            return "Error: " + e.getMessage();
        }
    }

    // POST请求接口，用于解密密码
    @PostMapping("/decrypt")
    public String decryptPassword(@RequestParam String encryptedPassword) {
        try {
            // 解码Base64加密字符串
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedPassword);
            // 获取密钥
            SecretKeySpec keySpec = new SecretKeySpec(encryptedBytes, ALGORITHM);
            // 获取Cipher实例
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            // 初始化Cipher为解密模式
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            // 解密密码
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            // 返回解密后的字符串
            return new String(decryptedBytes);
        } catch (Exception e) {
            // 错误处理
            return "Error: " + e.getMessage();
        }
    }
}
