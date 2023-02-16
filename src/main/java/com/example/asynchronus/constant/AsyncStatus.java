package com.example.asynchronus.constant;

public enum AsyncStatus {
    PROCESS("process"),
    FAIL("fail"),
    DONE("done"),
    ;

    AsyncStatus(String status) {
        this.status = status;
    }

    private String status;

    public String getStatus() {
        return status;
    }
}
