package com.vendingmachine.exceptions;

/**
 * Created by jmohammed on 10/24/15.
 */
public class ProductUnAvailableException extends RuntimeException {
    public ProductUnAvailableException(String s) {
        super(s);
    }

    public ProductUnAvailableException() {

    }
}
