package com.vendingmachine.states;

import com.vendingmachine.domain.Coin;
import com.vendingmachine.domain.Product;

public interface State {
    public void insertMoney(Coin coin);
    public void pressDispenseButton(String code);
    public Product dispenseProduct(String code);

}
