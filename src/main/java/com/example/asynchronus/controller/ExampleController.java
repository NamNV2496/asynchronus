package com.example.asynchronus.controller;

import com.example.asynchronus.service.AsyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExampleController {

    private final AsyncService asyncService;

    @GetMapping("/test")
    public String test() {
        return asyncService.asyncService();
    }
}
