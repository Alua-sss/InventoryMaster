package menu;

import controllers.interfaces.IStockTransactionController;
import menu.interfaces.Menu;

import java.util.Scanner;

public class TransactionMenu implements Menu {
    private final IStockTransactionController stockTransactionController;
    private final Scanner scanner = new Scanner(System.in);

    public TransactionMenu( IStockTransactionController stockTransactionController) {
        this.stockTransactionController = stockTransactionController;
    }

    @Override
    public void onLoad() {
        while (true) {
            System.out.println("\n--- Меню управления складом ---");
            System.out.println("1. Добавить приход товара (IN)");
            System.out.println("2. Добавить расход товара (OUT)");
            System.out.println("3. Просмотреть историю транзакций по продукту");
            System.out.println("0. Назад");
            System.out.print("Выберите опцию: ");

            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> stockTransactionController.recordTransaction("IN");
                    case 2 -> stockTransactionController.recordTransaction("OUT");
                    case 3 -> stockTransactionController.getFullStockTransactionDetails();
                    case 0 -> {
                        return;
                    }
                    default -> System.out.println("Неверный выбор. Попробуйте снова.");
                }
            } catch (Exception e) {
                System.out.println("Некорректный ввод. Введите число.");
                scanner.nextLine();
            }
        }
    }
    }

