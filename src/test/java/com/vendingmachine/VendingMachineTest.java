package com.vendingmachine;

import com.vendingmachine.domain.Coin;
import com.vendingmachine.domain.CoinType;
import com.vendingmachine.domain.Product;
import com.vendingmachine.exceptions.MachineException;
import com.vendingmachine.exceptions.ProductUnAvailableException;
import com.vendingmachine.parser.CoinParser;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VendingMachineTest {
    private VendingMachine vendingMachine;
    @Mock
    private AvailableProductBank mockAvailableProductBank;
    @Mock
    private State machineState;
    @Mock
    private CoinParser mockCoinParser;
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
        vendingMachine.setCoinParser(mockCoinParser);
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
