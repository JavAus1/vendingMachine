package com.vendingmachine.parser;

import com.vendingmachine.domain.Coin;
import com.vendingmachine.exceptions.InValidCoinTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class CoinParser {
    @Autowired
    private ICoinValidator coinValidator;

    public List<Coin> getCoinList() {
        return coinList;
    }

    private List<Coin> coinList = new ArrayList<Coin>();
    private Double totalInsertedAmount = 0.0;

    public void accept(Coin coin) {
        if (coinValidator.validate(coin)) {
            coinList.add(coin);
            totalInsertedAmount += coin.getCoinType().getCoinValue();
        }else{
            throw new InValidCoinTypeException("Invalid Coin Type");
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

    public void clearCoinsList() {
        coinList.clear();
    }
}
