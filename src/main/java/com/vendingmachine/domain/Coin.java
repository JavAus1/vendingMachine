package com.vendingmachine.domain;

import com.vendingmachine.PaymentType;
import com.vendingmachine.VendingMachine;
import com.vendingmachine.parser.CoinParser;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
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
