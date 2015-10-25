package com.vendingmachine;

import com.vendingmachine.domain.Coin;
import com.vendingmachine.domain.CoinType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CoinParserTest {
    private CoinParser coinParser;
    @Mock
    private CoinValidator mockCoinValidator;

    @Before
    public void setUp() throws Exception {
        coinParser = new CoinParser();
        coinParser.setCoinValidator(mockCoinValidator);
    }

    @Test
    public void invokesCoinValidator() throws Exception {
        Coin coin = new Coin();
        coin.setCoinType(CoinType.DIME);

        coinParser.accept(coin);

        verify(mockCoinValidator).validate(coin);
    }

    @Test
    public void acceptValidCoinAndStoresTotalAmount() throws Exception {
        Coin coin = new Coin();
        coin.setCoinType(CoinType.DIME);
        when(mockCoinValidator.validate(Matchers.<Coin>anyObject())).thenReturn(true);

        coinParser.accept(coin);

        assertThat(coinParser.getTotalInsertedAmount(),is(0.1));
    }
}