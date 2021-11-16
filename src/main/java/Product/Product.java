package Product;

public interface Product { // solId - Interface - объекты-продукты наследуются от интерфейса Product,
    double getPrice();      //получая необходимость реализовать базовые для работы магазина методы,
    String getName();       // остальные уникальные методы они реализуют от других интерфейсов
    int getQuantity();
    void setQuantity(int quantity);
    Product addToCart(int quantity);
}
