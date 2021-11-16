package Shop;

import Product.Product;

public interface ShopSection { // soliD - Dependency - расширяя адреса розничных магазинов,
    Product getProduct(int number);// можно создавать различные объекты секций, для работы с ними,
    String getAllAssortment();// например можно создать секции мягких игрушек и канцелярских предметов,
    int getAssortmentSize();// реализуя интерфейс ShopSection и добавить их в объект Shop
}
