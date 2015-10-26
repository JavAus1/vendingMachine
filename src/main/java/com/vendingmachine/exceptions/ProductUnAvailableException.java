package com.vendingmachine.exceptions;

public class ProductUnAvailableException extends RuntimeException {
    public ProductUnAvailableException(String message) {
        super(message);
    }
}
