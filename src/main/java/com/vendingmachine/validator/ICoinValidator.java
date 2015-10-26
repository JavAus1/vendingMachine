package com.vendingmachine.validator;

import com.vendingmachine.domain.Coin;

public interface ICoinValidator {
    public boolean validate(Coin coin);
}
