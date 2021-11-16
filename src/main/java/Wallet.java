public class Wallet {
    private double cash;

    public Wallet() {
        cash = 100000.00;
    }


    public double getCash() {
        return cash;
    }

    public void spend(double price) {
        cash = cash - price;
    }
}
