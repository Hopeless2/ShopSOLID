package Shop;

import Product.Product;
import Product.Fruit;

import java.util.ArrayList;
import java.util.List;

public class FruitSection implements ShopSection { // Solid - Single - объект класса FruitSection хранит ассортимент товаров интерфейса Fruit
    private List<Product> fruits;

    protected FruitSection(){
        fruits = new ArrayList<>();
        fruits.add(new Fruit(57.00, "2 недели", "Яблоко", 100));
        fruits.add(new Fruit(69.00, "2 недели", "Банан", 100));
        fruits.add(new Fruit(80.00, "2 недели", "Груша", 100));
        fruits.add(new Fruit(110.00, "2 недели", "Киви", 100));
        fruits.add(new Fruit(250.00, "2 недели", "Манго", 100));
        fruits.add(new Fruit(97.00, "2 недели", "Апельсин", 100));
        fruits.add(new Fruit(100.00, "2 недели", "Лимон", 100));
    }

    @Override
    public Product getProduct(int number){
        return fruits.get(number);
    }

    @Override
    public String getAllAssortment(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < fruits.size(); i++){
            sb.append((i + 1) + ". " + fruits.get(i).toString() + "\n");
        }
        return sb.toString();
    }

    public int getAssortmentSize(){
        return fruits.size();
    } // метод помогает избегать магических чисел
}
