package com.vendingmachine.dispensemotor;

import com.vendingmachine.domain.Product;

import java.util.List;

public interface DispenseMode {
    public Product dispenseSelectedProduct(List<Product> products, String productCode);


}
