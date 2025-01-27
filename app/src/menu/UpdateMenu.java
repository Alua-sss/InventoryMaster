package menu;

import menu.interfaces.Menu;
import models.Product;
import models.Warehouse;

import java.util.Scanner;

public class UpdateMenu implements Menu {

    private Warehouse warehouse;

    public UpdateMenu(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void onLoad() {

        System.out.print("Введите ID: ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        scanner.nextLine();

        Product currentProduct = warehouse.findProduct(id);

        if (currentProduct == null) {
            System.out.println("Товар с ID " + id + " не найден.");
            return;
        }

        while (true) {
            System.out.println("Что вы хотите поменять?");
            System.out.println("1. Название");
            System.out.println("2. Количество товара");
            System.out.println("3. Цену");
            System.out.println("4. Минимальное количество");
            System.out.println("5. Назад");
            System.out.print("Выберите опцию: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера
            switch (choice) {
                case 1:
                    System.out.print("Введите название: ");
                    String name = scanner.nextLine();
                    currentProduct.setName(name);
                    break;
                case 2:
                    System.out.println("Введите количество товара: ");
                    int quantity = scanner.nextInt();
                    currentProduct.setQuantity(quantity);
                    break;
                case 3:
                    System.out.println("Введите цену: ");
                    double price = scanner.nextDouble();
                    currentProduct.setPrice(price);
                    break;
                case 4:
                    System.out.println("Введите минимальное количество: ");
                    int minQuantity = scanner.nextInt();
                    currentProduct.setMinQuantity(minQuantity);
                    break;

                case 5:
                    return;
                default: break;
            }
        }
    }

}
