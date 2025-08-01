// 代码生成时间: 2025-08-01 12:26:53
package com.example.mathtools;
# 扩展功能模块

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class MathCalculationService {

    // Example calculation methods
    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {
# 优化算法效率
        if (b == 0) {
            throw new IllegalArgumentException("Cannot divide by zero.");
# 扩展功能模块
        }
        return a / b;
    }

    // Power method
    public double power(double base, double exponent) {
# 优化算法效率
        if (base < 0 && exponent % 1 != 0) {
            throw new IllegalArgumentException("Negative base with non-integer exponent is undefined.");
        }
        return Math.pow(base, exponent);
# 优化算法效率
    }

    // Additional mathematical operations can be added here
# 优化算法效率
    // ...
}