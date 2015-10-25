import java.util.List;

public class CoinInsertedState implements State {
    private VendingMachine vendingMachine;

    public CoinInsertedState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertMoney(Coin coin) {
        System.out.println("Amount inserted...." + vendingMachine.getTotalInsertedAmount());
        vendingMachine.insertMoney(coin);
    }

    @Override
    public void pressDispenseButton(String code) {
        System.out.println("Selected product..." + code);
        if (isProductAvailable(code) && sufficientAmountInserted(code))
            vendingMachine.setMachineState(vendingMachine.getDispenseState());
    }

    @Override
    public Product dispenseProduct(String code) {
        throw new MachineException("Press Dispense Button");
    }

    private boolean sufficientAmountInserted(String code) {
        Double difference = vendingMachine.getProduct(code).getPrice() - vendingMachine.getTotalInsertedAmount();
        if (difference <= 0) {
            return true;
        }
        throw new MachineException("Please insert more..." + difference);
    }

    private boolean isProductAvailable(String productCode) {
        Product product = vendingMachine.getProduct(productCode);
        if (product != null)
            return true;
        return false;
    }
}
