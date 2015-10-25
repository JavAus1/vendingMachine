package com.vendingmachine.dispensemotor;

import com.vendingmachine.domain.Product;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class FastDispenseMotor implements DispenseMode {
    @Override
    public Product dispenseSelectedProduct(List<Product> products, String productCode) {
        Product vendProduct;
        vendProduct = dispense(products);
        System.out.println("Product.." + productCode + "..delivered successfully");
        return vendProduct;
    }


    private Product dispense(List<Product> products) {
        return products.remove(products.size() - 1);
    }

}
