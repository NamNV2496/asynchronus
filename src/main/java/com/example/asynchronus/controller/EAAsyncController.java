package com.example.asynchronus.controller;

import com.example.asynchronus.service.EAAsync;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EAAsyncController {
    private final EAAsync eaAsync;


    @GetMapping("/eaAsync")
    public void eaAsync() {
        log.info("start: ");
        for (int i=0; i<5; i++) {
            long a = eaAsync.factorialUsingEAAsync(i);
//            log.info("the number: "+ a);
        }
        log.info("end");
    }
}
