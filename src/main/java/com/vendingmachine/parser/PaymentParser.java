package com.vendingmachine.parser;

import com.vendingmachine.domain.PaymentType;

public interface PaymentParser {
    public void accept(PaymentType paymentType);

    public Double clearAndDebitAmount();

    public Double getTotalInsertedAmount();
}
