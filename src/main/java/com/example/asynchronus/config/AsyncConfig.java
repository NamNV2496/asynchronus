package com.example.asynchronus.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
@RequiredArgsConstructor
@Slf4j
public class AsyncConfig {

    @NestedConfigurationProperty
    private final AsyncProperties asyncProperties;

    @Bean("asyncThreadPool")
    public ThreadPoolTaskExecutor connectorDepositTaskExecutor(){
        ThreadPoolTaskExecutor connectorDepositTaskExecutor = new ThreadPoolTaskExecutor();
        connectorDepositTaskExecutor.setThreadNamePrefix(asyncProperties.getThreadNamePrefix());
        connectorDepositTaskExecutor.setCorePoolSize(asyncProperties.getCorePoolSize());
        connectorDepositTaskExecutor.setMaxPoolSize(asyncProperties.getMaxPoolSize());
        connectorDepositTaskExecutor.setQueueCapacity(asyncProperties.getQueuePoolSize());
        connectorDepositTaskExecutor.initialize();
        return connectorDepositTaskExecutor;
    }
}