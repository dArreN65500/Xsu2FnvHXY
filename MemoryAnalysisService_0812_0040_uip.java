// 代码生成时间: 2025-08-12 00:40:42
package com.example.memoryanalysis;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

public class MemoryAnalysisService {

    /**
     * The MemoryMXBean interface is used to get the memory usage.
     */
    private final MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

    /**
     * Retrieves the memory usage of the JVM.
     *
     * @return a string representation of the memory usage.
     */
    public String getMemoryUsage() {
        try {
            MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
            MemoryUsage nonHeapMemoryUsage = memoryMXBean.getNonHeapMemoryUsage();

            return "Heap Memory Usage: 
" +
                   "  Initial: " + heapMemoryUsage.getInit() + " bytes
" +
                   "  Used: " + heapMemoryUsage.getUsed() + " bytes
" +
                   "  Committed: " + heapMemoryUsage.getCommitted() + " bytes
" +
                   "  Maximum: " + heapMemoryUsage.getMax() + " bytes
" +
                   "Non-Heap Memory Usage: 
" +
                   "  Initial: " + nonHeapMemoryUsage.getInit() + " bytes
" +
                   "  Used: " + nonHeapMemoryUsage.getUsed() + " bytes
" +
                   "  Committed: " + nonHeapMemoryUsage.getCommitted() + " bytes
" +
                   "  Maximum: " + nonHeapMemoryUsage.getMax() + " bytes
";
        } catch (Exception e) {
            // Log and handle exception appropriately
            // For simplicity, we return a simple error message here.
            return "Error retrieving memory usage: " + e.getMessage();
        }
    }
}
