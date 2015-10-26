package com.vendingmachine.productinventory;

import com.vendingmachine.domain.Product;
import com.vendingmachine.exceptions.MachineException;
import com.vendingmachine.exceptions.ProductUnAvailableException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;

@Component
public class AvailableProductBank implements ProductInventoryBank {

    @Getter
    @Setter
    private Map<String, List<Product>> availableProducts = new HashMap<String, List<Product>>();

    public void populateProduct(String code, Product product) {
        if (availableProducts.get(code) == null) {
            availableProducts.put(code, newArrayList(product));
        } else {
            availableProducts.get(code).add(product);
        }
    }

    public Product dispenseProduct(String productCode) {
        List<Product> products = availableProducts.get(productCode);
        if (products != null && products.size() > 0) {
            return products.remove(products.size() - 1);
        }
        throw new ProductUnAvailableException("Product Out of stock");
    }


    public int getCount(String code) {
        return availableProducts.get(code).size();
    }

    private Map<String, List<Product>> getAvailableProducts() {
        return availableProducts;
    }

    public Product getProduct(String productCode) {
        if (checkIfInventoryExists()) {
            List<Product> products = this.getAvailableProducts().get(productCode);
            if (isProductOutOfStock(products)) {
                throw new ProductUnAvailableException("Product Out of stock");
            }
            return products.get(products.size() - 1);
        }
        throw new MachineException("No products Present...Please come back later");
    }

    public List<Product> getListOfProducts(String productCode) {
        if (checkIfInventoryExists()) {
            List<Product> products = this.getAvailableProducts().get(productCode);
            if (isProductOutOfStock(products)) {
                throw new ProductUnAvailableException("Product Out of stock");
            }
            return products;
        }
        throw new MachineException("No products Present...Please come back later");
    }

    private boolean isProductOutOfStock(List<Product> products) {
        if (products != null) {
            return products.isEmpty();
        }
        return true;
    }

    private boolean checkIfInventoryExists() {
        if(this!=null) {
            return this.getAvailableProducts() != null;
        }
        return false;
    }

}
