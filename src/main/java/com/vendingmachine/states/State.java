package com.vendingmachine.states;

import com.vendingmachine.VendingMachine;
import com.vendingmachine.domain.Coin;
import com.vendingmachine.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class State {
    @Autowired
    VendingMachine vendingMachine;

    protected State(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    public abstract void insertMoney(Coin coin);
    public abstract void pressDispenseButton(String code);
    public abstract Product dispenseProduct(String code);
    public void cancel(){
        vendingMachine.getCoinParser().setTotalInsertedAmount(0.0);
        vendingMachine.setMachineState(vendingMachine.getNoCoinInsertedState());

    }

}
