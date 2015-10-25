package com.vendingmachine;

/**
 * Created by jmohammed on 10/25/15.
 */
public interface PaymentType {
    public void validateAndProcess(PaymentType paymentType);
}
