import Product.Product;
import Shop.ShopInMoscow;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Wallet wallet = new Wallet();
    static List<Product> inventory = new ArrayList<>();
    static ShopInMoscow shopInMoscow = ShopInMoscow.get();
    static Scanner scanner = new Scanner(System.in);
    static String input;


    public static void main(String[] args) {
        System.out.println("""
                Добро пожаловать в наш магазин!
                К сожалению у нас идет ремонт, поэтому доступны только 2 отдела
                """);
        chooseSection();

        label:
        while (true) {
            System.out.println("На вашем счету " + wallet.getCash() + "руб.\n" +
                    "Выберите действие:\n" +
                    "1 - Показать список товаров\n" +
                    "2 - Сменить отдел\n" +
                    "3 - Показать товары из корзины\n" +
                    "4 - Купить товары из корзины\n" +
                    "5 - Убрать товар из корзины\n" +
                    "6 - Показать купленные товары\n" +
                    "0 - Выйти из магазина");
            input = scanner.nextLine();
            switch (input) {
                case "1":
                    System.out.println(shopInMoscow.printAssortment());
                    addToCartProcess();
                    break;
                case "2":
                    chooseSection();
                    break;
                case "3":
                    if (shopInMoscow.getCart().isEmpty()) {
                        System.out.println("Корзина пуста!");
                    } else {
                        System.out.println("В корзине:\n" + shopInMoscow.printCart());
                    }
                    break;
                case "4":
                    if (shopInMoscow.getTotal() > wallet.getCash()) {
                        System.out.println("Не хватает денег, уберите что-то из корзины и попробуйте снова!");
                    } else {
                        wallet.spend(shopInMoscow.getTotal());
                        inventory.addAll(shopInMoscow.getCart());
                        System.out.println("Вы приобрели:\n" + shopInMoscow.printCart());
                        shopInMoscow.clearCart();
                    }
                    break;
                case "5":
                    if (shopInMoscow.getCart().isEmpty()) {
                        System.out.println("Корзина пуста!");
                    } else {
                        deleteProductIntoCart();
                    }
                    break;
                case "6":
                    if (inventory.isEmpty()) {
                        System.out.println("Вы ничего не купили!");
                    } else {
                        for (Product product : inventory) {
                            System.out.println(product.toString());
                        }
                    }
                    break;
                case "0":
                    System.out.println("До свидания! Приходите еще!");
                    break label;
                default:
                    System.out.println("Нет такого действия");
                    break;
            }

        }

    }

    public static void chooseSection() { // DRY
        while (true) {
            System.out.println("""
                    Выберите отдел:
                    1 - фруктовый отдел
                    2 - технический отдел""");
            input = scanner.nextLine();
            if (input.equals("1")) {
                shopInMoscow.chooseFruitSection();
                System.out.println("Вы попали во фруктовый отдел!");
                break;
            } else if (input.equals("2")) {
                shopInMoscow.chooseDigitalSection();
                System.out.println("Вы попали в технический отдел!");
                break;
            } else {
                System.out.println("Нет такого отдела");
            }
        }
    }

    public static void deleteProductIntoCart() {
        while (true) {
            System.out.println(shopInMoscow.printCart());
            System.out.println("Введите номер товара");
            int productNumber = Integer.parseInt(scanner.nextLine());
            if (productNumber > shopInMoscow.getCart().size() || productNumber <= 0) {
                System.out.println("Нет такого товара!");
            } else {
                if (shopInMoscow.getCart().get(productNumber - 1).getQuantity() == 1) {
                    System.out.println("Вы убрали из корзины: " + shopInMoscow.returnProductToAssortment(productNumber - 1, 1));
                    break;
                } else if (shopInMoscow.getCart().get(productNumber - 1).getQuantity() > 1) {
                    System.out.println("Введите кол-во товара");
                    int quantity = Integer.parseInt(scanner.nextLine());
                    if (quantity > shopInMoscow.getCart().get(productNumber - 1).getQuantity() || quantity <= 0) {
                        System.out.println("У нас столько нет!");
                    } else {
                        System.out.println("Вы убрали из корзины: " + shopInMoscow.returnProductToAssortment(productNumber - 1, quantity));
                        break;
                    }
                }
            }
        }
    }

    public static void addToCartProcess() {
        System.out.println("Хотите добавить что-то в корзину?(да или нет)");
        input = scanner.nextLine();
        if (input.equals("да")) {
            System.out.println("Выберите товар");
            int productNumber = Integer.parseInt(scanner.nextLine());
            if (productNumber > shopInMoscow.getAssortmentSize() || productNumber <= 0) {
                System.out.println("Нет такого товара!");
                return;
            }
            System.out.println("Выберите кол-во товара");
            int quantity = Integer.parseInt(scanner.nextLine());
            if (quantity > shopInMoscow.getProductQuantity(productNumber - 1) || quantity <= 0) {
                System.out.println("У нас столько нет!");
                return;
            }
            System.out.println("Вы положили в корзину: " + shopInMoscow.addProductToCart(productNumber - 1, quantity));
            if (shopInMoscow.isDigitalSection()) {
                System.out.println("Не хотите приобрести гарантию?");
                input = scanner.nextLine();
                if (input.equals("да")) {
                    if (quantity > 1) {
                        while (true) {
                            System.out.println("Введите кол-во товара, на которое хотите приобрести гарантию");
                            input = scanner.nextLine();
                            if (Integer.parseInt(input) > quantity) {
                                System.out.println("Вы ввели больше, чем купили");
                            } else if (Integer.parseInt(input) <= 0) {
                                System.out.println("Не совсем понял вас, ну да ладно");
                            } else {
                                quantity = Integer.parseInt(input);
                                break;
                            }
                        }
                    }
                    System.out.println("Вы положили в корзину: " + shopInMoscow.addWarrantyToCart(productNumber - 1, quantity));
                } else if (input.equals("нет")) {
                    System.out.println("Как скажете, возвращаю предыдущее меню");
                } else {
                    System.out.println("Не совсем понял вас, ну да ладно");
                }
            }

        } else if (input.equals("нет")) {
            System.out.println("Как скажете, возвращаю предыдущее меню");
        } else {
            System.out.println("Не совсем понял вас, ну да ладно");
        }
    }
}
