package com.vendingmachine.states;

import com.vendingmachine.VendingMachine;
import com.vendingmachine.domain.Coin;
import com.vendingmachine.domain.Product;
import com.vendingmachine.exceptions.MachineException;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CoinInsertedState extends State {

    public CoinInsertedState(VendingMachine vendingMachine) {
        super(vendingMachine);
    }

    @Override
    public void insertMoney(Coin coin) {
        vendingMachine.process(coin);
    }

    @Override
    public void pressDispenseButton(String code) {
        if (isProductAvailable(code) && sufficientAmountInserted(code))
            vendingMachine.setMachineState(vendingMachine.getDispenseState());
    }

    @Override
    public Product dispenseProduct(String code) {
        throw new MachineException("Press Dispense Button");
    }

    private boolean sufficientAmountInserted(String code) {
        Double difference = vendingMachine.getProduct(code).getPrice()
                - vendingMachine.getCoinParser().getTotalInsertedAmount();
        if (difference <= 0) {
            return true;

        }
        throw new MachineException("Please insert more..." + difference);
    }

    private boolean isProductAvailable(String productCode) {
        Product product = vendingMachine.getProduct(productCode);
        if (product != null)
            return true;
        return false;
    }


}
