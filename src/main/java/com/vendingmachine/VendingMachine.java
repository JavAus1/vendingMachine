package com.vendingmachine;

import com.vendingmachine.domain.Coin;
import com.vendingmachine.domain.Product;
import com.vendingmachine.exceptions.MachineException;
import com.vendingmachine.exceptions.ProductUnAvailableException;
import com.vendingmachine.parser.CoinParser;
import com.vendingmachine.productinventory.AvailableProductBank;
import com.vendingmachine.productinventory.ProductInventoryBank;
import com.vendingmachine.states.State;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
        if (products != null) {
            return products.isEmpty();
        }
        return true;
    }

    private boolean checkIfInventoryExists() {
        if(availableProductBank!=null) {
            return availableProductBank.getAvailableProducts() != null;
        }
        return false;
    }

    public ProductInventoryBank getAvailableProductBank() {
        return availableProductBank;
    }

    public void insertPayment(Coin coin) {
        machineState.insertMoney(coin);
    }

    public void process(Coin coin) {
        coinParser.accept(coin);
    }

    public Product pressButton(String code) {
        machineState.pressDispenseButton(code);
        return machineState.dispenseProduct(code);
    }

    public Double pressCancelButton() {
        return  machineState.cancel();
    }
}


