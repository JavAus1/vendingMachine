import java.util.List;

public class DispenseState implements State {
    private VendingMachine vendingMachine;

    public DispenseState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertMoney(Coin coin) {
        throw new MachineException("Currently Processing previous request");
    }

    @Override
    public void pressDispenseButton(String code) {
        throw new MachineException("Currently Processing previous request");
    }

    @Override
    public Product dispenseProduct(String code) {
        Product vendProduct;
        vendProduct = dispense(code);
        System.out.println("Product.." + code + "..delivered successfully");
        vendingMachine.setMachineState(vendingMachine.getNoCoinInsertedState());
        return vendProduct;
    }

    private Product dispense(String productCode) {
        List<Product> products = vendingMachine.getAvailableProductBank().getAvailableProducts().get(productCode);
        return products.remove(products.size() - 1);
    }
}
