package Shop;

import Product.Product;
import Product.Device;

import java.util.ArrayList;
import java.util.List;

public class DigitalSection implements ShopSection { // Solid - Single - объект класса DigitalSection хранит ассортимент товаров интерфейса Digital
    private List<Product> devices;

    protected DigitalSection(){
        devices = new ArrayList<>();
        devices.add(new Device(1500.00, "Наушники", 3));
        devices.add(new Device(15000.00, "Процессор", 5));
        devices.add(new Device(10000.00, "Монитор", 8));
        devices.add(new Device(500.00, "Термопаста", 10));
        devices.add(new Device(9999999999.00, "Видеокарта", 0));
        devices.add(new Device(19999.99, "Игровой компьютера 2 ядра 4 гига", 10));
    }
    @Override
    public Product getProduct(int number){
        return devices.get(number);
    }

    @Override
    public String getAllAssortment(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < devices.size(); i++){
            sb.append((i + 1) + ". " + devices.get(i).toString() + "\n");
        }
        return sb.toString();
    }

    public int getAssortmentSize(){
        return devices.size();
    } // метод помогает избегать магических чисел

    public Product buyWarranty(int number, int quantity){
        return new Device(
                (devices.get(number).getPrice() * 0.05),
                "Гарантия на " + devices.get(number).getName(),
                quantity);
    }
}
