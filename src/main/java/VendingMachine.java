import java.util.List;

public class VendingMachine {

    private double totalInsertedAmount;
    private AvailableProductBank availableProductBank;
    private State machineState = null;
    private State noCoinInsertedState = new NoCoinInsertedState(this);
    private State coinInsertedState = new CoinInsertedState(this);
    private State dispenseState = new DispenseState(this);

    public VendingMachine() {
        machineState = noCoinInsertedState;
    }

    public Product getProduct(String productCode) {
        if (checkIfInventoryExists()) {
            List<Product> products = availableProductBank.getAvailableProducts().get(productCode);
            if (isProductOutOfStock(products)) {
                throw new ProductUnAvailableException("Product Out of stock");
            }
            return products.get(products.size() - 1);
        }
        throw new MachineException("No products Present...Please come back later");
    }

    private boolean isProductOutOfStock(List<Product> products) {
        return products.isEmpty();
    }

    private boolean checkIfInventoryExists() {
        return availableProductBank.getAvailableProducts() != null;
    }

    public void setAvailableProductBank(AvailableProductBank availableProductBank) {
        this.availableProductBank = availableProductBank;
    }

    public void setMachineState(State machineState) {
        this.machineState = machineState;
    }

    public State getNoCoinInsertedState() {
        return noCoinInsertedState;
    }

    public State getCoinInsertedState() {
        return coinInsertedState;
    }

    public State getDispenseState() {
        return dispenseState;
    }

    public double getTotalInsertedAmount() {
        return totalInsertedAmount;
    }

    public AvailableProductBank getAvailableProductBank() {
        return availableProductBank;
    }

    public void insertMoney(Coin coin) {
        totalInsertedAmount += coin.getCoinType().getCoinValue();
    }

    public void pressButton(String code) {
        machineState.pressDispenseButton(code);
        machineState.dispenseProduct(code);
    }
}


