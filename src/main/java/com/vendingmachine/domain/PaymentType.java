package com.vendingmachine.domain;

import com.vendingmachine.VendingMachine;

public interface PaymentType {
    public void validateAndProcess(PaymentType paymentType, VendingMachine vendingMachine);
}
