// 代码生成时间: 2025-10-10 17:46:48
package com.example.creditscore;

import org.springframework.stereotype.Service;

// 信用评分服务类
@Service
public class CreditScoreService {

    // 计算信用评分的方法
    public double calculateCreditScore(double income, double expenses, double creditHistoryLength) {
        try {
            // 基本的信用评分逻辑，可以根据实际情况进行扩展
            // 这里只是一个简单的示例，实际模型会更复杂
            double score = (income / expenses) * (creditHistoryLength / 10);
            // 确保分数在0到1之间
            return Math.max(0, Math.min(score, 1));
        } catch (Exception e) {
            // 错误处理，记录日志等
            // 这里只是简单的打印异常信息，实际项目中应使用日志框架记录日志
            System.err.println("Error calculating credit score: " + e.getMessage());
            return -1;
        }
    }

    // 获取信用评分等级的方法
    public String getCreditScoreGrade(double score) {
        if (score < 0 || score > 1) {
            throw new IllegalArgumentException("Score must be between 0 and 1");
        }
        // 根据分数返回信用等级
        if (score >= 0.9) {
            return "Excellent";
        } else if (score >= 0.7) {
            return "Good";
        } else if (score >= 0.5) {
            return "Average";
        } else if (score >= 0.3) {
            return "Poor";
        } else {
            return "Very Poor";
        }
    }
}
