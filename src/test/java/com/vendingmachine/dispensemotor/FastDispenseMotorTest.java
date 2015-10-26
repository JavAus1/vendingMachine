package com.vendingmachine.dispensemotor;

import com.vendingmachine.TestUtils;
import com.vendingmachine.domain.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class FastDispenseMotorTest {
    private FastDispenseMotor fastDispenseMotor;

    @Before
    public void setUp() throws Exception {
        fastDispenseMotor = new FastDispenseMotor();
    }

    @Test
    public void dispenseProduct() throws Exception {
        Map<String, List<Product>> inventory = TestUtils.buildSingleProductInventory();

        fastDispenseMotor.dispenseSelectedProduct(inventory.get("A1"), "A1");

        assertEquals(0, inventory.get("A1").size());
    }

}