package com.vendingmachine;

import com.vendingmachine.VendingMachine;
import com.vendingmachine.domain.Coin;
import com.vendingmachine.domain.CoinType;
import com.vendingmachine.domain.Product;
import com.vendingmachine.exceptions.InValidCoinTypeException;
import com.vendingmachine.exceptions.MachineException;
import com.vendingmachine.exceptions.ProductUnAvailableException;
import com.vendingmachine.productinventory.AvailableProductBank;
import com.vendingmachine.productinventory.ProductInventoryBank;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;

public class IntegrationTest {
    private VendingMachine vendingMachine;
    private ProductInventoryBank productInventoryBank;
    private Map<String, List<Product>> productInventory;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("/applicatonContext.xml");
       vendingMachine = (VendingMachine) context.getBean("vendingMachine");
        vendingMachine.setMachineState(vendingMachine.getNoCoinInsertedState());
        productInventory = new HashMap<String, List<Product>>();
        productInventoryBank = new AvailableProductBank();
        productInventoryBank.populateProduct("A1",new Product("A1","pepsi", 0.50));
        productInventoryBank.populateProduct("A2",new Product("A2","chips", 1.0));
        productInventoryBank.populateProduct("A3",new Product("A3","cookies", 0.25));
        vendingMachine.setAvailableProductBank(productInventoryBank);
    }

    @Test
    public void correctlyDispenseOneItem() throws Exception {
        Coin coin = new Coin();
        coin.setCoinType(CoinType.QUARTER);
        vendingMachine.insertPayment(coin);
        Product product = vendingMachine.pressButton("A3");

        assertEquals("A3",product.getCode());
    }

    @Test
    public void correctlyDispenseOneItemWithTwoCoinsInserted() throws Exception {
        Coin coin1 = new Coin();
        coin1.setCoinType(CoinType.QUARTER);
        Coin coin2 = new Coin();
        coin2.setCoinType(CoinType.QUARTER);
        vendingMachine.insertPayment(coin1);
        vendingMachine.insertPayment(coin2);

        Product product = vendingMachine.pressButton("A1");

        assertEquals("A1",product.getCode());
    }

    @Test
    public void correctlyDispenseItemWithThreeCoinsInserted() throws Exception {
        Coin coin1 = new Coin();
        coin1.setCoinType(CoinType.QUARTER);
        Coin coin2 = new Coin();
        coin2.setCoinType(CoinType.QUARTER);
        Coin coin3 = new Coin();
        coin3.setCoinType(CoinType.NICKEL);
        vendingMachine.insertPayment(coin1);
        vendingMachine.insertPayment(coin2);
        vendingMachine.insertPayment(coin3);

        Product product = vendingMachine.pressButton("A2");

        assertEquals("A2",product.getCode());

    }

    /*@Test
    public void throwsExceptionWheInvalidCoinIsInserted() throws Exception {
        Coin coin1 = new Coin();
        coin1.setCoinType(CoinType.PENNY);
        vendingMachine.insertPayment(coin1);
        thrown.expect(InValidCoinTypeException.class);
//        thrown.expectMessage("Invalid Coin Type");

        vendingMachine.pressButton("A2");

    }
*/
    @Test
    public void throwsExceptionWhenEnoughAmountIsNotInserted() throws Exception {
        Coin coin1 = new Coin();
        coin1.setCoinType(CoinType.QUARTER);
        vendingMachine.insertPayment(coin1);
        thrown.expect(MachineException.class);
        thrown.expectMessage("Please insert more..." + 0.75);

        vendingMachine.pressButton("A2");
    }

    @Test
    public void throwsExceptionWhenInvalidCodeIsEntered() throws Exception {
        Coin coin1 = new Coin();
        coin1.setCoinType(CoinType.QUARTER);
        vendingMachine.insertPayment(coin1);
        thrown.expect(ProductUnAvailableException.class);
        thrown.expectMessage("Product Out of stock");

        vendingMachine.pressButton("A4");
    }

    @Test
    public void throwsExceptionWhenProductIsOutOfStock() throws Exception {
        Coin coin1 = new Coin();
        coin1.setCoinType(CoinType.QUARTER);
        vendingMachine.insertPayment(coin1);
        productInventoryBank.getAvailableProducts().get("A3").clear();
        thrown.expect(ProductUnAvailableException.class);
        thrown.expectMessage("Product Out of stock");

        vendingMachine.pressButton("A3");
    }

    @Test
    public void throwsExceptionWhenButtonIsPressedBeforeInsertingMoney() throws Exception {
        thrown.expect(MachineException.class);
        thrown.expectMessage("Not Applicable..Please insert money and press Dispense button");

        vendingMachine.pressButton("A3");
    }

}
