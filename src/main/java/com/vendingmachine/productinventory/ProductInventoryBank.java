package com.vendingmachine.productinventory;

import com.vendingmachine.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

public interface ProductInventoryBank {
    public void populateProduct(String code, Product product);
    public Product dispenseProduct(String productCode);
    public Map<String, List<Product>> getAvailableProducts();


}
