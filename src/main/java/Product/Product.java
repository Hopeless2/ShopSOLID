package Product;

public interface Product {
    double getPrice();
    String getName();
    int getQuantity();
    void setQuantity(int quantity);
    Product addToCart(int quantity);
}
