package com.vendingmachine;

import com.vendingmachine.domain.Product;
import com.vendingmachine.exceptions.MachineException;
import com.vendingmachine.exceptions.ProductUnAvailableException;
import com.vendingmachine.productinventory.AvailableProductBank;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;

public class AvailableProductBankTest {

    private AvailableProductBank availableProductBank;
    @Rule
    public ExpectedException thrown = ExpectedException.none();


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

    @Test(expected = ProductUnAvailableException.class)
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

    @Test
    public void correctlyReturnsTheRequestProduct() {
        Product product = new Product("A1", "pepsi", 10.0);
        availableProductBank.populateProduct("A1", product);

        assertEquals("A1", availableProductBank.getProduct("A1").getCode());
    }

    @Test
    public void correctlyReturnsListOfAvailableProductsForAGivenProductCode() {
        Product product = new Product("A1", "pepsi", 10.0);
        availableProductBank.populateProduct("A1", product);

        assertEquals(newArrayList(new Product("A1", "pepsi", 10.0)), availableProductBank.getListOfProducts("A1"));
    }

    @Test
    public void throwsExceptionWhenProductIsOutOfStock() {
        List<Product> productList = new ArrayList<Product>();
        Map<String, List<Product>> availableProducts = new HashMap<String, List<Product>>();
        availableProducts.put("A1", productList);
        thrown.expect(ProductUnAvailableException.class);

        availableProductBank.getProduct("A1").getCode();
    }

    @Test
    public void throwsExceptionWhenNoProductInventory() throws Exception {
        availableProductBank.setAvailableProducts(null);
        thrown.expect(MachineException.class);
        thrown.expectMessage("No products Present...Please come back later");

        availableProductBank.getProduct("A1").getCode();
    }


}