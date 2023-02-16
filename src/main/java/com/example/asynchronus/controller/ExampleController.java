package com.example.asynchronus.controller;

import com.example.asynchronus.service.AsyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ExampleController {

    private final AsyncService asyncService;

    @GetMapping("/test")
    public String test() {
        List<String> list = List.of("task1", "task2", "task3");
        asyncService.doAsync(list);
        return "OK";
    }
}
