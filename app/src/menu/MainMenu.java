package menu;

import models.Warehouse;

import models.Product;
import services.AuthService;

import java.util.Scanner;

public class MainMenu implements Menu {
    @Override
    public void onLoad() {

        AuthService authService = AuthService.getInstance();

        Warehouse warehouse = new Warehouse();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Показать все товары");
            System.out.println("2. Добавить товар (только Admin)");
            System.out.println("3. Удалить товар (только Admin)");
            System.out.println("4. Показать товары с низкими запасами");
            System.out.println("5. Общая стоимость товаров");
            System.out.println("6. Изменить товар");
            System.out.println("7. Выйти");
            System.out.print("Выберите опцию: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println(warehouse.getProducts());
                    break;

                case 2:
                    if (!authService.isAdmin()) {
                        System.out.println("Недостаточно прав для выполнения этой операции.");
                        break;
                    }
                    System.out.print("Введите ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Введите название: ");
                    String name = scanner.nextLine();
                    System.out.print("Введите количество: ");
                    int quantity = scanner.nextInt();
                    System.out.print("Введите цену: ");
                    double price = scanner.nextDouble();
                    System.out.print("Введите минимальное количество: ");
                    int minQuantity = scanner.nextInt();

                    warehouse.addProduct(new Product(id, name, price, quantity, minQuantity));
                    break;

                case 3:
                    if (!authService.isAdmin()) {
                        System.out.println("Недостаточно прав для выполнения этой операции.");
                        break;
                    }
                    System.out.print("Введите ID товара для удаления: ");
                    int removeId = scanner.nextInt();
                    warehouse.removeProduct(removeId);
                    break;
                case 4:
                    warehouse.getLowStockProducts();
                    break;

                case 5:
                    double totalValue = warehouse.calculateTotalValue();
                    System.out.println("Общая стоимость товаров: " + totalValue);
                    break;

                case 6:
                    if (!authService.isAdmin()) {
                        System.out.println("Недостаточно прав для выполнения этой операции.");
                        break;
                    }
                    warehouse.updateProduct();
                    break;

                case 7:
                    authService.logout();
                    return;

                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }

    }

}
