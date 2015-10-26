package com.vendingmachine;

import com.vendingmachine.domain.Coin;
import com.vendingmachine.domain.Product;
import com.vendingmachine.parser.CoinParser;
import com.vendingmachine.productinventory.ProductInventoryBank;
import com.vendingmachine.states.State;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class VendingMachine {

    private State machineState;
    @Autowired
    private ProductInventoryBank availableProductBank;
    @Autowired
    private CoinParser coinParser;
    @Autowired
    private State noCoinInsertedState;
    @Autowired
    private State coinInsertedState;
    @Autowired
    private State dispenseState;

    public VendingMachine() {
        machineState = noCoinInsertedState;
    }

    public void insertPayment(Coin coin) {
        machineState.insertMoney(coin);
    }

    public Product pressButton(String code) {
        machineState.pressDispenseButton(code);
        return machineState.dispenseProduct(code);
    }

    public Double pressCancelButton() {
        return machineState.cancel();
    }

    public void process(Coin coin) {
        coinParser.accept(coin);
    }

    public boolean isProductAvailable(String productCode) {
        return availableProductBank.getProduct(productCode) != null;
    }
}


