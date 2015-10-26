package com.vendingmachine;

import com.vendingmachine.domain.Coin;
import com.vendingmachine.domain.CoinType;
import com.vendingmachine.exceptions.InValidCoinTypeException;
import com.vendingmachine.parser.CoinParser;
import com.vendingmachine.parser.PaymentParser;
import com.vendingmachine.parser.CoinValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
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
        when(mockCoinValidator.validate(Matchers.<Coin>anyObject())).thenReturn(true);

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
        assertEquals(1,coinParser.getCoinList().size());
    }

    @Test(expected = InValidCoinTypeException.class)
    public void throwsExceptionIfInvalidCoinType() throws Exception {
        Coin coin = new Coin();
        coin.setCoinType(CoinType.PENNY);
        when(mockCoinValidator.validate(Matchers.<Coin>anyObject())).thenReturn(false);

        coinParser.accept(coin);
    }

    @Test
    public void clearCoinsListWhenTransactionIsCancelled() throws Exception {
        Coin coin1 = new Coin();
        coin1.setCoinType(CoinType.PENNY);
        Coin coin2 = new Coin();
        coin2.setCoinType(CoinType.DIME);
        when(mockCoinValidator.validate(Matchers.<Coin>anyObject())).thenReturn(true).thenReturn(true);
        coinParser.accept(coin1);
        coinParser.accept(coin2);

        coinParser.clearAndDebitAmount();

        assertEquals(0,coinParser.getCoinList().size());
    }

    /*
    @Test
    public void returnsExtraCoinsAfterDispense() throws Exception {
        Coin coin1 = new Coin();
        coin1.setCoinType(CoinType.DIME);
        Coin coin2 = new Coin();
        coin2.setCoinType(CoinType.QUARTER);
        paymentParser.accept(coin1);
        paymentParser.accept(coin2);
        when(mockCoinValidator.validate(Matchers.<Coin>anyObject())).thenReturn(true);

        List<Coin> coinList = paymentParser.remove(new Double(0.25));

        assertThat(coinList.get(0).getCoinType().getCoinValue(),is(0.25));
    }*/
}