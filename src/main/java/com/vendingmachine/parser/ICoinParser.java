package com.vendingmachine.parser;

import com.vendingmachine.domain.Coin;

/**
 * Created by jmohammed on 10/25/15.
 */
public interface ICoinParser {
    public void accept(Coin coin);

}
