package com.vendingmachine.productinventory;

import com.vendingmachine.domain.Product;

import java.util.List;
import java.util.Map;

public interface ProductInventoryBank {
    public void populateProduct(String code, Product product);

    public Product dispenseProduct(String productCode);

    public Product getProduct(String productCode);

    public List<Product> getListOfProducts(String productCode);


}
