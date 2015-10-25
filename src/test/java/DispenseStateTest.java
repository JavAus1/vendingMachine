import com.vendingmachine.*;
import com.vendingmachine.dispensemotor.DispenseMode;
import com.vendingmachine.domain.Coin;
import com.vendingmachine.domain.Product;
import com.vendingmachine.exceptions.MachineException;
import com.vendingmachine.states.DispenseState;
import com.vendingmachine.states.NoCoinInsertedState;
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

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DispenseStateTest {

    private DispenseState dispenseState;
    @Mock
    private VendingMachine mockVendingMachine;
    @Mock
    private AvailableProductBank mockAvailableProductBank;
    @Mock
    private DispenseMode mockDispenseMode;
    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Before
    public void setUp() {
        dispenseState = new DispenseState(mockVendingMachine);
        dispenseState.setDispenseMode(mockDispenseMode);
    }

    @Test
    public void throwsExceptionWhenTryingToInsertMoneyAtTheTimeOfDispense() {
        thrown.expect(MachineException.class);
        thrown.expectMessage("Currently Processing previous request");

        dispenseState.insertMoney(new Coin());
    }

    @Test
    public void correctlyInvokesDispenseTheProduct() throws Exception {
        Map<String, List<Product>> inventory = TestUtils.buildSingleProductInventory();
        when(mockVendingMachine.getListOfProducts("A1")).thenReturn(inventory.get("A1"));

        dispenseState.dispenseProduct("A1");

        verify(mockDispenseMode).dispenseSelectedProduct(anyList(), anyString());
    }

    @Test
    public void changesStateAfterDispense() throws Exception {
        Map<String, List<Product>> inventory = TestUtils.buildSingleProductInventory();
        when(mockVendingMachine.getAvailableProductBank()).thenReturn(mockAvailableProductBank);
        when(mockVendingMachine.getListOfProducts("A1")).thenReturn(inventory.get("A1"));

        dispenseState.dispenseProduct("A1");

        verify(mockVendingMachine).setMachineState(Matchers.<NoCoinInsertedState>anyObject());
    }
}