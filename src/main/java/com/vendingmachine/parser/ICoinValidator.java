package com.vendingmachine.parser;

import com.vendingmachine.domain.Coin;

/**
 * Created by jmohammed on 10/25/15.
 */
public interface ICoinValidator {
    public boolean validate(Coin coin);
}
