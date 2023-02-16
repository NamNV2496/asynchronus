package com.example.asynchronus.service;

import com.example.asynchronus.constant.AsyncStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AsyncService {

    public String doAsync(List<String> requests) {
        String transaction = AsyncStatus.PROCESS.getStatus();
        List<AsyncStatus> listResponse;
        try {
            List<CompletableFuture<AsyncStatus>> allResponseCallback= new ArrayList<>();
            for (String request : requests) {
                allResponseCallback.add(this.asyncService(request));
            }

            listResponse = this.getAllCompleted(allResponseCallback, 10000, TimeUnit.SECONDS);
            log.info("[TEST] list response callback : {}", listResponse);
            if (CollectionUtils.isEmpty(listResponse)) {
                return AsyncStatus.FAIL.getStatus();
            }
            transaction = AsyncStatus.DONE.getStatus();
        } catch (Exception e) {
            return AsyncStatus.FAIL.getStatus();
        }
        return transaction;
    }

    @Async("asyncThreadPool")
    public CompletableFuture<AsyncStatus> asyncService(String request) {
        try {
            log.info("thread: {}, run request: {}", Thread.currentThread().getName(), request);
            return CompletableFuture.completedFuture(this.actionNeedRunMultiThread());
        } catch (Exception ex) {
            log.error("asyncService: Exception {}", ex.getMessage());
            return CompletableFuture.completedFuture(AsyncStatus.FAIL);
        }
    }


    private AsyncStatus actionNeedRunMultiThread() {
        try {
            log.info("action need to run in multi thread");
            return AsyncStatus.DONE;
        } catch (Exception e) {
            return AsyncStatus.FAIL;
        }
    }

    private <T> List<T> getAllCompleted(List<CompletableFuture<T>> futuresList, long timeout, TimeUnit unit) {
        CompletableFuture<Void> allFuturesResult = CompletableFuture.allOf(futuresList.toArray(new CompletableFuture[0]));
        try {
            allFuturesResult.get(timeout, unit);
        } catch (InterruptedException ie) {
            log.error("ConcurrentUtils - getAllCompleted with InterruptedException {}", ie.getMessage());
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            log.error("ConcurrentUtils - getAllCompleted with Exception {}", e.getMessage());
        }
        return futuresList.stream()
                .filter(future -> future.isDone() && !future.isCompletedExceptionally()) // keep only the ones completed
                .map(CompletableFuture::join) // get the value from the completed future
                .collect(Collectors.<T>toList()); // collect as a list
    }

}
