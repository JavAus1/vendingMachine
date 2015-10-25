import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestUtils {
    public static Map<String, List<Product>> buildSingleProductInventory() {
        List<Product> productList = new ArrayList<Product>();
        productList.add(new Product("A1", "pepsi", 1.0));
        Map<String, List<Product>> availableProducts = new HashMap<String, List<Product>>();
        availableProducts.put("A1", productList);
        return availableProducts;
    }
}
