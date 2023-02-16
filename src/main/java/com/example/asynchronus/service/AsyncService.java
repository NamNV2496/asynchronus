package com.example.asynchronus.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class AsyncService {

    @Async("asyncThreadPool")
    public String asyncService() {
        log.info("thread: " + Thread.currentThread().getName());
        return LocalDateTime.now().toString();
    }

}
