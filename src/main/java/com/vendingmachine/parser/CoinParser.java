package com.vendingmachine.parser;

import com.vendingmachine.domain.Coin;
import com.vendingmachine.exceptions.InValidCoinTypeException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class CoinParser {
    @Autowired
    @Setter
    private ICoinValidator coinValidator;

    public List<Coin> getCoinList() {
        return coinList;
    }

    private List<Coin> coinList = new ArrayList<Coin>();
    @Getter
    @Setter
    private Double totalInsertedAmount = 0.0;

    public void accept(Coin coin) {
        if (coinValidator.validate(coin)) {
            coinList.add(coin);
            totalInsertedAmount += coin.getCoinType().getCoinValue();
        } else {
            throw new InValidCoinTypeException("Invalid Coin Type");
        }
    }

    public void clearCoinsList() {
        coinList.clear();
    }
}
