package com.vendingmachine.states;

import com.vendingmachine.domain.PaymentType;
import com.vendingmachine.VendingMachine;
import com.vendingmachine.domain.Product;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
@EqualsAndHashCode
public abstract class State {
    @Autowired
    VendingMachine vendingMachine;

    protected State(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    public abstract void insertMoney(PaymentType paymentType);

    public abstract void pressDispenseButton(String code);

    public abstract Product dispenseProduct(String code);

    public Double cancel() {
        return vendingMachine.getPaymentParser().clearAndDebitAmount();
    }

}
