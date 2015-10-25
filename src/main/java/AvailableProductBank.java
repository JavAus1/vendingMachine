import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;

public class AvailableProductBank {

    private Map<String, List<Product>> availableProducts = new HashMap<String, List<Product>>();

    public Product dispenseProduct(String productCode) {
        List<Product> products = availableProducts.get(productCode);
        if (products != null && products.size() > 0) {
            return products.remove(products.size() - 1);
        }
        throw new ProductUnAvailableException("Product Unavailable");
    }

    public void populateProduct(String code, Product product) {
        if (availableProducts.get(code) == null) {
            availableProducts.put(code, newArrayList(product));
        } else {
            availableProducts.get(code).add(product);
        }
    }

    public int getCount(String code) {
        return availableProducts.get(code).size();
    }
}
