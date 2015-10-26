import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NoCoinInsertedStateTest {

    private NoCoinInsertedState noCoinInsertedState;
    @Mock
    private VendingMachine mockVendingMachine;
    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Before
    public void setUp() {
        noCoinInsertedState = new NoCoinInsertedState(mockVendingMachine );
    }

    @Test
    public void correctlyInvokesAcceptCoin() throws Exception {
        noCoinInsertedState.insertMoney(new Coin());

        verify(mockVendingMachine).insertMoney(Matchers.<Coin>anyObject());
    }

    @Test
    public void changesMachineStateWhenCoinIsInserted() {
        CoinInsertedState coinInsertedState = new CoinInsertedState(mockVendingMachine);
        when(mockVendingMachine.getCoinInsertedState()).thenReturn(coinInsertedState);

        noCoinInsertedState.insertMoney(new Coin());

        verify(mockVendingMachine).setMachineState(Matchers.<CoinInsertedState>anyObject());
    }

    @Test
    public void throwsExceptionWhenDispenseMethodIsInvoked() throws Exception {
        thrown.expect(MachineException.class);
        thrown.expectMessage("Not Applicable..Please insert money and press Dispense button");
        noCoinInsertedState.dispenseProduct("");
    }

    @Test
    public void throwsExceptionWhenDispenseButtonIsPressedWithoutInsertingMoney() throws Exception {
        thrown.expect(MachineException.class);
        thrown.expectMessage("Not Applicable..Please insert money and press Dispense button");
        noCoinInsertedState.dispenseProduct("");
    }
}