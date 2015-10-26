package com.vendingmachine.parser;

import com.vendingmachine.domain.Coin;
import com.vendingmachine.domain.CoinType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class CoinValidator implements ICoinValidator{
    public boolean validate(Coin coin) {
        return getValidCoinTypes().contains(coin.getCoinType());
    }

    private List<CoinType> getValidCoinTypes() {
        List<CoinType> validCoinTypes = new ArrayList<CoinType>();
        validCoinTypes.add(CoinType.NICKEL);
        validCoinTypes.add(CoinType.DIME);
        validCoinTypes.add(CoinType.QUARTER);
        return validCoinTypes;
    }
}
