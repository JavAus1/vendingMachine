package com.vendingmachine.states;

import com.vendingmachine.VendingMachine;
import com.vendingmachine.dispensemotor.DispenseMode;
import com.vendingmachine.domain.Coin;
import com.vendingmachine.domain.Product;
import com.vendingmachine.exceptions.MachineException;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@NoArgsConstructor
public class DispenseState extends State {
    @Resource(name = "fastDispenseMotor")
    private DispenseMode dispenseMode;

    public DispenseState(VendingMachine vendingMachine) {
        super(vendingMachine);
    }

    @Override
    public void insertMoney(Coin coin) {
        throw new MachineException("Currently Processing previous request");

    }

    @Override
    public void pressDispenseButton(String code) {
        throw new MachineException("Currently Processing previous request");
    }

    @Override
    public Product dispenseProduct(String code) {
        List<Product> products = vendingMachine.getListOfProducts(code);
        Product vendProduct = dispenseMode.dispenseSelectedProduct(products, code);
//        vendingMachine.getCoinParser()
        vendingMachine.setMachineState(vendingMachine.getNoCoinInsertedState());
        return vendProduct;
    }

    public void setDispenseMode(DispenseMode dispenseMode) {
        this.dispenseMode = dispenseMode;
    }
}
