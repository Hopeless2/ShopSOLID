package Product;

import java.util.Objects;

public class Device implements Product, Digital {
    private final double price;
    private final String name;
    private int quantity;
    private static final String MARKUP = " ## ";

    public Device(double price, String name, int quantity){
        this.price = price;
        this.name = name;
        this.quantity = quantity;
    }

    public Product addToCart(int quantity){
        this.quantity -= quantity;
        return new Device(this.price, this.name, quantity);
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public int getQuantity(){
        return quantity;
    }

    @Override
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + MARKUP +
                "Цена: " + price + MARKUP +
                "В наличии: " + quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return Double.compare(device.price, price) == 0 && Objects.equals(name, device.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, name);
    }
}
