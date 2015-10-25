
public interface State {
    public void insertMoney(Coin coin);
    public void pressDispenseButton(String code);
    public Product dispenseProduct(String code);

}
