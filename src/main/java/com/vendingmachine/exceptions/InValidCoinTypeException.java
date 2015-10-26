package com.vendingmachine.exceptions;

public class InValidCoinTypeException extends RuntimeException {
    public InValidCoinTypeException(String message) {
        super(message);
    }
}
