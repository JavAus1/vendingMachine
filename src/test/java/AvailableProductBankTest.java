import com.vendingmachine.AvailableProductBank;
import com.vendingmachine.domain.Product;
import com.vendingmachine.exceptions.ProductUnAvailableException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AvailableProductBankTest {

    private AvailableProductBank availableProductBank;

    @Before
    public void setUp() {
        availableProductBank = new AvailableProductBank();
    }

    @Test
    public void addsNewProductToProductBank() {
        Product product = new Product("A1", "pepsi", 10.0);
        availableProductBank.populateProduct("A1", product);

        assertEquals(1, availableProductBank.getCount("A1"));
    }

    @Test
    public void appendsProductToProductBank() {
        Product product = new Product("A1", "pepsi", 10.0);
        availableProductBank.populateProduct("A1", product);
        availableProductBank.populateProduct("A1", product);

        assertEquals(2, availableProductBank.getCount("A1"));
    }

    @Test
    public void retrievesProductForGivenCode() {
        Product product = new Product("A1", "pepsi", 10.0);
        availableProductBank.populateProduct("A1", product);

        Product retrieveProduct = availableProductBank.dispenseProduct("A1");

        assertEquals("pepsi", retrieveProduct.getName());
    }

    @Test(expected=ProductUnAvailableException.class)
    public void throwsExceptionIfProductDoestNotExist() {
        Product product = new Product("A1", "pepsi", 10.0);
        availableProductBank.populateProduct("A1", product);

        availableProductBank.dispenseProduct("A1");
        availableProductBank.dispenseProduct("A1");
    }

    @Test
    public void decrementsCountOfProductsAfterDispense() {
        Product product = new Product("A1", "pepsi", 10.0);
        availableProductBank.populateProduct("A1", product);
        availableProductBank.populateProduct("A1", product);

        availableProductBank.dispenseProduct("A1");

        assertEquals(1, availableProductBank.getCount("A1"));
    }

}