/**
 * Created by jmohammed on 10/24/15.
 */
public class VendingMachine {

    private double totalInsertedAmount;
    private AvailableProductBank availableProductBank;

    public double accept(Coin coin) {
        totalInsertedAmount += coin.getCoinType().getCoinValue();
        return totalInsertedAmount;
    }

    public Product selectProduct(String productCode) {
        return availableProductBank.dispenseProduct(productCode);
    }

    public void setAvailableProductBank(AvailableProductBank availableProductBank) {
        this.availableProductBank = availableProductBank;
    }
}


