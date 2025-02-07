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
            System.out.println("\n--- Warehouse management menu ---");
            System.out.println("1. Add the arrival of products (IN)");
            System.out.println("2. Add products consumption (OUT)");
            System.out.println("3. View the history of transactions by product");
            System.out.println("0. Back");
            System.out.print("Choose the action: ");

            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> stockTransactionController.recordTransaction("IN");
                    case 2 -> stockTransactionController.recordTransaction("OUT");
                    case 3 -> stockTransactionController.getFullStockTransactionDetails();
                    case 0 -> {
                        return;
                    }
                    default -> System.out.println("The wrong choice. Try it again.");
                }
            } catch (Exception e) {
                System.out.println("Incorrect input. Please enter the number.");
                scanner.nextLine();
            }
        }
    }
    }

