package com.vendingmachine.domain;

public enum CoinType {

    QUARTER(0.25),
    DIME(0.1),
    PENNY(0.01),
    NICKEL(0.5);

    private double coinValue;

    CoinType(double coinValue) {
        this.coinValue = coinValue;
    }

    public double getCoinValue() {
        return coinValue;
    }
}
