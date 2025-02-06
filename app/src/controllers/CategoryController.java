package controllers;

import controllers.interfaces.ICategoryController;
import models.Category;
import services.interfaces.ICategoryService;

import java.util.List;
import java.util.Scanner;

public class CategoryController implements ICategoryController {
    private final ICategoryService categoryService;
    private final Scanner scanner = new Scanner(System.in);

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void addCategory() {
        System.out.println("\n----------");

        try {
            System.out.print("Enter the name of the category: ");
            String name = scanner.nextLine();
            if (categoryService.addCategory(name)) {
                System.out.println("The category is successfully added!");
            } else {
                System.out.println("Error when adding a category.");
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }

        System.out.println("----------");
    }

    @Override
    public void getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();

        System.out.println("\n----------");

        if (categories.isEmpty()) {
            System.out.println("The list of categories is empty.");
        } else {
            System.out.println("The list of categories is:");
            for (Category category : categories) {
                System.out.println(category.getId() + ": " + category.getName());
            }
        }

        System.out.println("----------");

    }

    @Override
    public void getCategoryById() {
        System.out.println("\n----------");
        System.out.print("Enter category id: ");

        int id = scanner.nextInt();

        Category category = categoryService.getCategoryById(id);

        if (category != null) {
            System.out.println(category);
        } else {
            System.out.println("Error when getting category by id.");
        }

        System.out.println("----------");
    }
}
