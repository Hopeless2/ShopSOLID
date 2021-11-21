package Shop;

import Product.Product;

public interface ShopSection {
    Product getProduct(int number);
    String getAllAssortment();
    int getAssortmentSize();
}
