package com.vendingmachine;

import com.vendingmachine.domain.Coin;
import com.vendingmachine.domain.CoinType;
import com.vendingmachine.validator.CoinValidator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CoinValidatorTest {

    private CoinValidator coinValidator;

    @Before
    public void setUp() throws Exception {
        coinValidator = new CoinValidator();
    }

    @Test
    public void isValidCoinType() throws Exception {
        Coin coin = new Coin();
        coin.setCoinType(CoinType.DIME);

        assertTrue(coinValidator.validate(coin));
    }

    @Test
    public void isInvalidCoinType() throws Exception {
        Coin coin = new Coin();
        coin.setCoinType(CoinType.PENNY);

        assertFalse(coinValidator.validate(coin));
    }
}