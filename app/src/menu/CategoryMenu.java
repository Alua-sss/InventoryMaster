package menu;

import controllers.interfaces.ICategoryController;
import menu.interfaces.Menu;

import java.util.Scanner;

public class CategoryMenu implements Menu {
    private final ICategoryController categoryController;
    private final Scanner scanner = new Scanner(System.in);

    public CategoryMenu(ICategoryController categoryController) {
        this.categoryController = categoryController;
    }

    @Override
    public void onLoad() {
        while (true) {
            System.out.println("\n--- Category management ---");
            System.out.println("1. Show all categories");
            System.out.println("2. Add the category");
            System.out.println("3. Search category");
            System.out.println("0. Back");
            System.out.print("Choose the action: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> categoryController.getAllCategories();
                    case 2 -> categoryController.addCategory();
                    case 3 -> categoryController.getCategoryById();
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
