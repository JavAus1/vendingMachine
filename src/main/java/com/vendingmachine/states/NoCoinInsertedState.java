package com.vendingmachine.states;

import com.vendingmachine.*;
import com.vendingmachine.domain.Coin;
import com.vendingmachine.domain.Product;
import com.vendingmachine.exceptions.MachineException;
import org.springframework.stereotype.Component;

@Component
public class NoCoinInsertedState extends State {

    public NoCoinInsertedState() {
    }

    public NoCoinInsertedState(VendingMachine vendingMachine) {
        super(vendingMachine);
    }

    @Override
    public void insertMoney(Coin coin) {
        vendingMachine.process(coin);
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
}
