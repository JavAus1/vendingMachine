import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DispenseStateTest {

    private DispenseState dispenseState;
    @Mock
    private VendingMachine mockVendingMachine;
    @Mock
    private AvailableProductBank mockAvailableProductBank;
    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Before
    public void setUp() {
        dispenseState = new DispenseState(mockVendingMachine);
    }

    @Test
    public void throwsExceptionWhenTryingToInsertMoneyAtTheTimeOfDispense() {
        thrown.expect(MachineException.class);
        thrown.expectMessage("Currently Processing previous request");

        dispenseState.insertMoney(new Coin());
    }

    @Test
    public void correctlyDispenseTheProduct() throws Exception {
        Map<String, List<Product>> inventory = TestUtils.buildSingleProductInventory();
        when(mockVendingMachine.getAvailableProductBank()).thenReturn(mockAvailableProductBank);
        when(mockAvailableProductBank.getAvailableProducts()).thenReturn(inventory);

        Product vendItem = dispenseState.dispenseProduct("A1");

        assertEquals("A1", vendItem.getCode());
    }

    @Test
    public void changesStateAfterDispense() throws Exception {
        Map<String, List<Product>> inventory = TestUtils.buildSingleProductInventory();
        when(mockVendingMachine.getAvailableProductBank()).thenReturn(mockAvailableProductBank);
        when(mockAvailableProductBank.getAvailableProducts()).thenReturn(inventory);

        dispenseState.dispenseProduct("A1");

        verify(mockVendingMachine).setMachineState(Matchers.<NoCoinInsertedState>anyObject());
    }
}