public class NoCoinInsertedState implements State {
    private VendingMachine vendingMachine;

    public NoCoinInsertedState(VendingMachine vendingMachine) {
        this.vendingMachine =vendingMachine;
    }

    @Override
    public void insertMoney(Coin coin) {
        vendingMachine.insertMoney(coin);
        System.out.println("Amount inserted...." + vendingMachine.getTotalInsertedAmount());
        vendingMachine.setMachineState(vendingMachine.getCoinInsertedState());
    }

    @Override
    public void pressDispenseButton(String code) {
        throw new MachineException("Not Applicable..Please insert money and press Dispense button");
    }

    @Override
    public Product dispenseProduct(String code) {
        throw new MachineException("Not Applicable..Please insert money and press Dispense button");
    }
}
