package com.vendingmachine.parser;

import com.vendingmachine.PaymentType;

public interface PaymentParser {
    public void accept(PaymentType paymentType);

    public Double clearAndDebitAmount();

    public Double getTotalInsertedAmount();
}
