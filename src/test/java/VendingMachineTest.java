import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
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
    }

    @Test
    public void acceptsSingleCoin() {
        Coin coin = new Coin();
        coin.setCoinType(CoinType.DIME);

        vendingMachine.insertMoney(coin);
        assertThat(vendingMachine.getTotalInsertedAmount(), is(0.1));
    }

    @Test
    public void acceptsMultiplesCoins() {
        Coin coin1 = new Coin();
        coin1.setCoinType(CoinType.DIME);
        Coin coin2 = new Coin();
        coin2.setCoinType(CoinType.DIME);

        vendingMachine.insertMoney(coin1);
        vendingMachine.insertMoney(coin2);
        assertThat(vendingMachine.getTotalInsertedAmount(), is(0.2));
    }

    @Test
    public void correctlyReturnsTheRequestProduct() {
        Map<String, List<Product>> availableProducts = TestUtils.buildSingleProductInventory();
        when(mockAvailableProductBank.getAvailableProducts()).thenReturn(availableProducts);

        assertEquals("A1", vendingMachine.getProduct("A1").getCode());
    }

    @Test
    public void throwsExceptionWhenProductIsOutOfStock() {
        List<Product> productList = new ArrayList<Product>();
        Map<String, List<Product>> availableProducts = new HashMap<String, List<Product>>();
        availableProducts.put("A1", productList);
        when(mockAvailableProductBank.getAvailableProducts()).thenReturn(availableProducts);
        thrown.expect(ProductUnAvailableException.class);

        vendingMachine.getProduct("A1").getCode();
    }

    @Test
    public void correctlyInvokesDispenseProduct() throws Exception {
        vendingMachine.pressButton("A1");
        verify(machineState).dispenseProduct("A1");
    }
}
