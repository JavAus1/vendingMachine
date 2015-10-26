package com.vendingmachine.domain;

import lombok.Getter;

public enum CoinType {

    QUARTER(0.25),
    DIME(0.1),
    PENNY(0.01),
    NICKEL(0.5);

    @Getter
    private double coinValue;

    CoinType(double coinValue) {
        this.coinValue = coinValue;
    }

}
