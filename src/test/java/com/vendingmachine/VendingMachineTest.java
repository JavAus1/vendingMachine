package com.vendingmachine;

import com.vendingmachine.domain.Coin;
import com.vendingmachine.domain.CoinType;
import com.vendingmachine.parser.PaymentParser;
import com.vendingmachine.productinventory.AvailableProductBank;
import com.vendingmachine.states.State;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class VendingMachineTest {
    private VendingMachine vendingMachine;
    @Mock
    private AvailableProductBank mockAvailableProductBank;
    @Mock
    private State machineState;
    @Mock
    private PaymentParser mockCoinParser;
    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void canaryTest() {
        assertTrue(true);
    }

    @Before
    public void setUp() {
        vendingMachine = new VendingMachine();
        vendingMachine.setAvailableProductBank(mockAvailableProductBank);
        vendingMachine.setMachineState(machineState);
        vendingMachine.setPaymentParser(mockCoinParser);
    }

    @Test
    public void invokesCoinParser() {
        Coin coin = new Coin();
        coin.setCoinType(CoinType.DIME);

        vendingMachine.process(coin);

        verify(mockCoinParser).accept(Matchers.<Coin>anyObject());
    }


    @Test
    public void correctlyInvokesDispenseProduct() throws Exception {
        vendingMachine.pressButton("A1");
        verify(machineState).dispenseProduct("A1");
    }
}
