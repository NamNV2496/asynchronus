package com.example.asynchronus.service;


import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.ea.async.Async;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class EAAsync {
    public EAAsync() {
        Async.init();
    }

    public long factorialUsingEAAsync(int number) {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> add(number,2)).thenApply(n -> printResult(n));
//        return Async.await(completableFuture);
        return 1L;
    }


    @SneakyThrows
    public static int add(int a, int b) {
        int sum = a + b;
        Thread.sleep(1000 * a);
        return sum;
    }

    private int printResult(int sum) {
        log.info("Callback is called. The sum = " + sum);
        return sum;
    }
}
