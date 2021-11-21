package Shop;

import Product.Product;
import Product.Device;

import java.util.ArrayList;
import java.util.List;

public class ShopInMoscow implements Shop{
    FruitSection fruitSection;
    DigitalSection digitalSection;
    List<Product> cart = new ArrayList<>();
    private static boolean isDigital;
    private static ShopInMoscow instance = null;

    private ShopInMoscow(){
        fruitSection = new FruitSection();
        digitalSection = new DigitalSection();
    }

    public static ShopInMoscow get(){
        if (instance == null) instance = new ShopInMoscow();
        return instance;
    }

    public String printAssortment(){
        if(isDigital){
            return digitalSection.getAllAssortment();
        }else{
            return fruitSection.getAllAssortment();
        }
    }

    public void chooseFruitSection(){
        isDigital = false;
    }

    public void chooseDigitalSection(){
        isDigital = true;
    }

    public String addProductToCart(int number, int quantity){
        if(isDigital){
            cart.add(digitalSection.getProduct(number).addToCart(quantity));
        }else{
            cart.add(fruitSection.getProduct(number).addToCart(quantity));
        }
        return cart.get(cart.size() - 1).getName() + " " + cart.get(cart.size() - 1).getQuantity() + "шт";
    }

    public String addWarrantyToCart(int number, int quantity){
        cart.add(digitalSection.buyWarranty(number, quantity));
        return cart.get(cart.size() - 1).getName() + " " + cart.get(cart.size() - 1).getQuantity() + "шт";
    }

    public boolean isDigitalSection(){
        return isDigital;
    }

    public double getTotal(){
        if(cart.isEmpty()){
            System.out.println("Корзина пуста!");
            return 0;
        }else{
            double total = 0.00;
            for(int i = 0; i < cart.size(); i++){
                total += cart.get(i).getPrice() * cart.get(i).getQuantity();
            }
            return total;
        }
    }

    public List<Product> getCart(){
        return cart;
    }

    public String printCart(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < cart.size(); i++){
            sb.append((i + 1) + ". " + cart.get(i).toString() + "\n");
        }
        return sb.toString();
    }

    public void clearCart(){
        cart.clear();
    }

    public int getAssortmentSize(){
        if(isDigital){
            return digitalSection.getAssortmentSize();
        }else{
            return fruitSection.getAssortmentSize();
        }
    }

    public int getProductQuantity(int number){
        if(isDigital){
            return digitalSection.getProduct(number).getQuantity();
        }else{
            return fruitSection.getProduct(number).getQuantity();
        }
    }

    public String returnProductToAssortment(int number, int quantity){
        String response;
        if(cart.get(number).getClass().equals(Device.class)){
            if(cart.get(number).getName().startsWith("Гарантия")){
                if(quantity == cart.get(number).getQuantity()){
                    response = cart.get(number).getName() + " " + quantity + "шт";
                    cart.remove(number);
                }else{
                    response = cart.get(number).getName() + " " + quantity + "шт";
                    cart.get(number).setQuantity(cart.get(number).getQuantity() - quantity);
                }
                return response;
            }
            for(int i = 0; i < digitalSection.getAssortmentSize(); i++){
                if(digitalSection.getProduct(i).hashCode() == cart.get(number).hashCode()){
                    digitalSection.getProduct(i).setQuantity(digitalSection.getProduct(i).getQuantity() + cart.get(number).getQuantity());
                    if(quantity == cart.get(number).getQuantity()){
                        response = cart.get(number).getName() + " " + quantity + "шт";
                        cart.remove(number);
                    }else{
                        response = cart.get(number).getName() + " " + quantity + "шт";
                        cart.get(number).setQuantity(cart.get(number).getQuantity() - quantity);
                    }
                    return response;
                }
            }
        }else{
            for(int i = 0; i < fruitSection.getAssortmentSize(); i++){
                if(fruitSection.getProduct(i).hashCode() == cart.get(i).hashCode()){
                    fruitSection.getProduct(i).setQuantity(fruitSection.getProduct(i).getQuantity() + cart.get(number).getQuantity());
                    if(quantity == cart.get(number).getQuantity()){
                        response = cart.get(number).getName() + " " + quantity + "шт";
                        cart.remove(number);
                    }else{
                        response = cart.get(number).getName() + " " + quantity + "шт";
                        cart.get(number).setQuantity(cart.get(number).getQuantity() - quantity);
                    }
                    return response;
                }
            }
        }
        return null;
    }

}
