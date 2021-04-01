package com.example.demo;

public class WithdrawalException extends RuntimeException {
    public WithdrawalException(String errorCode) {
        super(errorCode);

    }
}
