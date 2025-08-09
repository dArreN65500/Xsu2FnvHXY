// 代码生成时间: 2025-08-09 19:42:25
 * @author [Your Name]
 * @version 1.0
 * @date [Today's Date]
 */
package com.yourcompany.memoryanalysis;

import org.springframework.stereotype.Service;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

/**
 * Service class to perform memory usage analysis.
 */
@Service
public class MemoryAnalysisService {

    private final MemoryMXBean memoryMXBean;

    /**
     * Constructor to get an instance of MemoryMXBean.
     */
    public MemoryAnalysisService() {
        this.memoryMXBean = ManagementFactory.getMemoryMXBean();
    }

    /**
     * Analyzes the current memory usage.
     * 
     * @return MemoryUsage details as a string.
     */
    public String analyzeMemoryUsage() {
        try {
            MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
            MemoryUsage nonHeapMemoryUsage = memoryMXBean.getNonHeapMemoryUsage();

            StringBuilder memoryUsageInfo = new StringBuilder();
            memoryUsageInfo.append("Heap Memory Usage:
");
            memoryUsageInfo.append("  Initial: ").append(heapMemoryUsage.getInit()).append("bytes
");
            memoryUsageInfo.append("  Used: ").append(heapMemoryUsage.getUsed()).append("bytes
");
            memoryUsageInfo.append("  Committed: ").append(heapMemoryUsage.getCommitted()).append("bytes
");
            memoryUsageInfo.append("  Maximum: ").append(heapMemoryUsage.getMax()).append("bytes
");
            memoryUsageInfo.append("Non-Heap Memory Usage:
");
            memoryUsageInfo.append("  Initial: ").append(nonHeapMemoryUsage.getInit()).append("bytes
");
            memoryUsageInfo.append("  Used: ").append(nonHeapMemoryUsage.getUsed()).append("bytes
");
            memoryUsageInfo.append("  Committed: ").append(nonHeapMemoryUsage.getCommitted()).append("bytes
");
            memoryUsageInfo.append("  Maximum: ").append(nonHeapMemoryUsage.getMax()).append("bytes");

            return memoryUsageInfo.toString();
        } catch (Exception e) {
            // Log the exception and return an error message
            return "Error analyzing memory usage: " + e.getMessage();
        }
    }
}
