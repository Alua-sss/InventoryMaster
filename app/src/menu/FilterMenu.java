package menu;

import controllers.interfaces.IFilterController;
import menu.interfaces.Menu;

import java.util.Scanner;

public class FilterMenu implements Menu {
    private final IFilterController filterController;
    private final Scanner scanner = new Scanner(System.in);

    public FilterMenu(IFilterController filterController) {
        this.filterController = filterController;
    }

    @Override
    public void onLoad() {
        while (true) {
            System.out.println("\n--- Filtering menu ---");
            System.out.println("1. Filter by category");
            System.out.println("2. Filter by price");
            System.out.println("3. Filter by quantity");
            System.out.println("4. Filter by name");
            System.out.println("0. Back");
            System.out.print("Choose the action: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> filterController.filterByCategory();
                    case 2 -> filterController.filterByPriceRange();
                    case 3 -> filterController.filterByQuantityRange();
                    case 4 -> filterController.filterByName();
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
