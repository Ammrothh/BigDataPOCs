package com.viewmanagementservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class AsyncConfig {

    @Value("${async.executor.core-pool-size}")
    private int corePoolSize;

    @Value("${async.executor.max-pool-size}")
    private int maxPoolSize;

    @Value("${async.executor.queue-capacity}")
    private int queueCapacity;

    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix("EventExecutor-");
        executor.initialize();
        return executor;
    }
}
