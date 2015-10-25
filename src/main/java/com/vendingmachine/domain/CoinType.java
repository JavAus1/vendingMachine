package com.vendingmachine.domain;

import lombok.Getter;

/**
 * Created by jmohammed on 10/24/15.
 */
public enum CoinType {

    QUARTER(0.25),
    DIME(0.10),
    NICKEL(0.5);

    private double coinValue;

    CoinType(double coinValue) {
        this.coinValue = coinValue;
    }

    public double getCoinValue() {
        return coinValue;
    }
}
