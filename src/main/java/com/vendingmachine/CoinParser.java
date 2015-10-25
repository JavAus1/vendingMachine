package com.vendingmachine;

import com.vendingmachine.domain.Coin;

import java.util.ArrayList;
import java.util.List;

public class CoinParser {
    private CoinValidator coinValidator;
    private List<Coin> coins = new ArrayList<Coin>();
    private Double totalInsertedAmount =0.0;

    public void accept(Coin coin) {
        if (coinValidator.validate(coin)) {
            coins.add(coin);
            totalInsertedAmount += coin.getCoinType().getCoinValue();
        }
    }

    public void setCoinValidator(CoinValidator coinValidator) {
        this.coinValidator = coinValidator;
    }

    public double getTotalInsertedAmount() {
        return totalInsertedAmount;
    }

    public void setTotalInsertedAmount(Double totalInsertedAmount) {
        this.totalInsertedAmount = totalInsertedAmount;
    }
}
