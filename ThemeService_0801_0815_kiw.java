// 代码生成时间: 2025-08-01 08:15:29
package com.example.themeservice;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;

// 主题切换服务
@Service
public class ThemeService {

    // 保存当前会话的主题设置
    public void setTheme(HttpSession session, String theme) {
        if (session == null || theme == null) {
            throw new IllegalArgumentException("Session or theme cannot be null");
        }
        session.setAttribute("theme", theme);
    }

    // 获取当前会话的主题设置
    public String getTheme(HttpSession session) {
        if (session == null) {
            throw new IllegalArgumentException("Session cannot be null");
        }
        return (String) session.getAttribute("theme");
    }
}

// REST控制器，处理主题切换请求
@RestController
public class ThemeController {

    private final ThemeService themeService;

    public ThemeController(ThemeService themeService) {
        this.themeService = themeService;
    }

    // 更新主题设置
    @PutMapping("/set-theme")
    public String setTheme(@RequestBody String theme, HttpSession session) {
        try {
            themeService.setTheme(session, theme);
            return "Theme updated to: " + theme;
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        }
    }

    // 获取当前主题设置
    @GetMapping("/get-theme")
    public String getTheme(HttpSession session) {
        try {
            return "Current theme: " + themeService.getTheme(session);
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        }
    }
}