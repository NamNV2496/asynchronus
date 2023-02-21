package com.example.asynchronus.controller;

import com.example.asynchronus.service.AsyncService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ExampleController {

    private final AsyncService asyncService;

    @GetMapping("/test")
    public String test() {
        List<String> list = List.of("task1", "task2", "task3", "task4", "task5", "task6", "task7", "task8", "task9", "task10", "task11", "task12");
        log.info("Start");
        asyncService.doAsync(list);
        log.info("End");
        return "OK";
    }

    @SneakyThrows
    @GetMapping("/test2")
    public String test2() {
        log.info("Start");
        CompletableFuture<String> employeeName = asyncService.getEmployeeName();
        CompletableFuture<String> employeePhone = asyncService.getEmployeePhone();

//        log.info("EmployeeName--> " + employeeName.get());
//        log.info("EmployeePhone--> " + employeePhone.get());
        log.info("End");
        return "OK";
    }
}
