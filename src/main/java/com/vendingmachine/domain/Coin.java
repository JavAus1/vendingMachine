package com.vendingmachine.domain;

import com.vendingmachine.VendingMachine;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Coin implements PaymentType {
    private CoinType coinType;

    @Override
    public void validateAndProcess(PaymentType paymentType, VendingMachine vendingMachine) {
        vendingMachine.process(paymentType);
    }
}
