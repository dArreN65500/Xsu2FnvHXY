// 代码生成时间: 2025-08-12 20:49:24
package com.example.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import java.lang.management.ThreadInfo;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
public class SystemPerformanceMonitor {

    private static final String METRICS_URL = "/metrics";

    @GetMapping(METRICS_URL)
    public Map<String, Object> getMetrics() {
        // Collect runtime and thread metrics
        Map<String, Object> metrics = collectRuntimeMetrics();
        collectThreadMetrics(metrics);
        return metrics;
    }

    /**
     * Collects runtime metrics.
     *
     * @return A map of runtime metrics.
     */
    private Map<String, Object> collectRuntimeMetrics() {
        // RuntimeMXBean provides information about the Java runtime
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        // Initialize a map to hold metrics
        Map<String, Object> metrics = Map.of(
            "startTime", runtimeMXBean.getStartTime(),
            "uptime", runtimeMXBean.getUptime(),
            "specName", runtimeMXBean.getSpecName(),
            "specVendor", runtimeMXBean.getSpecVendor(),
            "specVersion", runtimeMXBean.getSpecVersion(),
            "vmName", runtimeMXBean.getVmName(),
            "vmVendor", runtimeMXBean.getVmVendor(),
            "vmVersion", runtimeMXBean.getVmVersion()
        );
        return metrics;
    }

    /**
     * Collects thread metrics.
     *
     * @param metrics The metrics map to populate with thread metrics.
     */
    private void collectThreadMetrics(Map<String, Object> metrics) {
        // ThreadMXBean provides information about Java threads
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        // Get the thread info for all threads
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(true, true);
        // Initialize a list to hold thread metrics
        List<Map<String, String>> threads = List.of();
        for (ThreadInfo threadInfo : threadInfos) {
            threads.add(Map.of(
                "threadId", String.valueOf(threadInfo.getThreadId()),
                "threadName", threadInfo.getThreadName(),
                "blockedTime", String.valueOf(threadInfo.getBlockedTime()),
                "blockedCount", String.valueOf(threadInfo.getBlockedCount()),
                "waitedTime", String.valueOf(threadInfo.getWaitedTime()),
                "waitedCount", String.valueOf(threadInfo.getWaitedCount())
            ));
        }
        metrics.put("threads", threads);
    }

    public static void main(String[] args) {
        // Run the Spring Boot application
        SpringApplication.run(SystemPerformanceMonitor.class, args);
    }
}
