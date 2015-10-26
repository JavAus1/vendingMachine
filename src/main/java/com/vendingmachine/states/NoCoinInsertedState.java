package com.vendingmachine.states;

import com.vendingmachine.PaymentType;
import com.vendingmachine.VendingMachine;
import com.vendingmachine.domain.Product;
import com.vendingmachine.exceptions.MachineException;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class NoCoinInsertedState extends State {

    public NoCoinInsertedState(VendingMachine vendingMachine) {
        super(vendingMachine);
    }

    @Override
    public void insertMoney(PaymentType paymentType) {
        paymentType.validateAndProcess(paymentType,vendingMachine);
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
