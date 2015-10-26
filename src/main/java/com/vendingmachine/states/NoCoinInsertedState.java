package com.vendingmachine.states;

import com.vendingmachine.*;
import com.vendingmachine.domain.Coin;
import com.vendingmachine.domain.Product;
import com.vendingmachine.exceptions.MachineException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NoCoinInsertedState implements State {
    @Autowired
    private VendingMachine vendingMachine;

    public NoCoinInsertedState(VendingMachine vendingMachine) {
        this.vendingMachine =vendingMachine;
    }

    @Override
    public void insertMoney(Coin coin) {
        vendingMachine.insertMoney(coin);
        vendingMachine.setMachineState(vendingMachine.getCoinInsertedState());
    }

    @Override
    public void pressDispenseButton(String code) {
        throw new MachineException("Not Applicable..Please insert money and press Dispense button");
    }

    @Override
    public Product dispenseProduct(String code) {
        throw new MachineException("Not Applicable..Please insert money and press Dispense button");
    }

    public void setVendingMachine(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }
}
