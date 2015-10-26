package com.vendingmachine;

public interface PaymentType {
    public void validateAndProcess(PaymentType paymentType, VendingMachine vendingMachine);
}
