package Shop;

import Product.Product;

import java.util.List;

public interface Shop {
    String printAssortment();
    String addProductToCart(int number, int quantity);
    double getTotal();
    List<Product> getCart();
    String printCart();
    void clearCart();
    int getAssortmentSize();
    int getProductQuantity(int number);
    String returnProductToAssortment(int number, int quantity);
}
