import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class VendingMachineTest {
    private VendingMachine vendingMachine;
    @Mock
    private AvailableProductBank mockAvailableProductBank;

    @Test
    public void canaryTest() {
        assertTrue(true);
    }

    @Before
    public void setUp() {
        vendingMachine = new VendingMachine();
        vendingMachine.setAvailableProductBank(mockAvailableProductBank);
    }

    @Test
    public void acceptsSingleCoin() {
        Coin coin = new Coin();
        coin.setCoinType(CoinType.DIME);

        assertThat(vendingMachine.accept(coin), is(0.1));
    }

    @Test
    public void correctlyInvokesTheProductBank() {
        vendingMachine.selectProduct("A1");

        verify(mockAvailableProductBank).dispenseProduct(Matchers.<String>anyObject());
    }

}
