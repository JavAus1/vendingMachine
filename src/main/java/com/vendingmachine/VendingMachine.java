package com.vendingmachine;

import com.vendingmachine.domain.Coin;
import com.vendingmachine.domain.Product;
import com.vendingmachine.exceptions.MachineException;
import com.vendingmachine.exceptions.ProductUnAvailableException;
import com.vendingmachine.states.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VendingMachine {

    private double totalInsertedAmount;
    private AvailableProductBank availableProductBank;
    private State machineState = null;
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

    public Product getProduct(String productCode) {
        if (checkIfInventoryExists()) {
            List<Product> products = availableProductBank.getAvailableProducts().get(productCode);
            if (isProductOutOfStock(products)) {
                throw new ProductUnAvailableException("Product Out of stock");
            }
            return products.get(products.size() - 1);
        }
        throw new MachineException("No products Present...Please come back later");
    }

    public List<Product> getListOfProducts(String productCode) {
        if (checkIfInventoryExists()) {
            List<Product> products = availableProductBank.getAvailableProducts().get(productCode);
            if (isProductOutOfStock(products)) {
                throw new ProductUnAvailableException("Product Out of stock");
            }
            return products;
        }
        throw new MachineException("No products Present...Please come back later");
    }

    private boolean isProductOutOfStock(List<Product> products) {
        return products.isEmpty();
    }

    private boolean checkIfInventoryExists() {
        return availableProductBank.getAvailableProducts() != null;
    }

    public void setAvailableProductBank(AvailableProductBank availableProductBank) {
        this.availableProductBank = availableProductBank;
    }

    public void setMachineState(State machineState) {
        this.machineState = machineState;
    }

    public double getTotalInsertedAmount() {
        return totalInsertedAmount;
    }

    public AvailableProductBank getAvailableProductBank() {
        return availableProductBank;
    }

    public void insertMoney(Coin coin) {
        coinParser.accept(coin);
        totalInsertedAmount += coin.getCoinType().getCoinValue();
    }

    public void pressButton(String code) {
        machineState.pressDispenseButton(code);
        machineState.dispenseProduct(code);
    }

    public State getNoCoinInsertedState() {
        return noCoinInsertedState;
    }

    public State getCoinInsertedState() {
        return coinInsertedState;
    }

    public State getDispenseState() {
        return dispenseState;
    }

    public void setTotalInsertedAmount(double totalInsertedAmount) {
        this.totalInsertedAmount = totalInsertedAmount;
    }

    public void setCoinParser(CoinParser coinParser) {
        this.coinParser = coinParser;
    }
}


