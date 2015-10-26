package com.vendingmachine.states;

import com.vendingmachine.productinventory.AvailableProductBank;
import com.vendingmachine.TestUtils;
import com.vendingmachine.VendingMachine;
import com.vendingmachine.domain.Coin;
import com.vendingmachine.domain.Product;
import com.vendingmachine.exceptions.MachineException;
import com.vendingmachine.parser.CoinParser;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CoinInsertedStateTest {

    private CoinInsertedState coinInsertedState;
    @Mock
    private VendingMachine mockVendingMachine;
    @Mock
    private AvailableProductBank mockAvailableProductBank;
    @Mock
    private CoinParser mockCoinParser;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        coinInsertedState = new CoinInsertedState(mockVendingMachine);
    }

    @Test
    public void correctlyInvokesToAcceptCoinInVendingMachine() {
        coinInsertedState.insertMoney(new Coin());

        verify(mockVendingMachine).process(Matchers.<Coin>anyObject());
    }

    @Test
    public void changesMachineStateToDispenseWhenButtonIsPressed() {
        when(mockVendingMachine.getCoinParser()).thenReturn(mockCoinParser);
        when(mockCoinParser.getTotalInsertedAmount()).thenReturn(1.0);
        when(mockVendingMachine.getAvailableProductBank()).thenReturn(mockAvailableProductBank);
        when(mockVendingMachine.isProductAvailable("A1")).thenReturn(true);
        when(mockAvailableProductBank.getProduct("A1")).thenReturn(new Product("A1", "pepsi", 1.0));

        coinInsertedState.pressDispenseButton("A1");

        verify(mockVendingMachine).setMachineState(Matchers.<DispenseState>anyObject());
    }

    @Test
    public void throwsExceptionWhenTryingToDispenseWithNotEnoughMoney() {
        when(mockVendingMachine.getCoinParser()).thenReturn(mockCoinParser);
        when(mockVendingMachine.isProductAvailable("A1")).thenReturn(true);
        when(mockVendingMachine.getAvailableProductBank()).thenReturn(mockAvailableProductBank);
        when(mockAvailableProductBank.getProduct("A1")).thenReturn(new Product("A1", "pepsi", 1.0));
        when(mockCoinParser.getTotalInsertedAmount()).thenReturn(0.6);
        thrown.expect(MachineException.class);
        thrown.expectMessage("Please insert more...0.4");

        coinInsertedState.pressDispenseButton("A1");
    }

}