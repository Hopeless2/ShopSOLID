package Product;

import java.util.Objects;

public class Fruit implements Product, Food {
    private final double price;
    private final String shelfLife;
    private final String name;
    private int quantity;
    private static final String MARKUP = " ## ";

    public Fruit(double price, String shelfLife, String name, int quantity) {
        this.price = price;
        this.shelfLife = shelfLife;
        this.name = name;
        this.quantity = quantity;
    }

    public Product addToCart(int quantity) {
        this.quantity -= quantity;
        return new Fruit(this.price, this.shelfLife, this.name, quantity);
    }


    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return name + MARKUP +
                "Срок годности: " + shelfLife + MARKUP +
                "Цена: " + price + MARKUP +
                "В наличии: " + quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fruit fruit = (Fruit) o;
        return Double.compare(fruit.price, price) == 0 && Objects.equals(shelfLife, fruit.shelfLife) && Objects.equals(name, fruit.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, shelfLife, name);
    }
}
