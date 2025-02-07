package controllers;

import controllers.interfaces.ICategoryController;
import controllers.interfaces.IFilterController;
import models.Category;
import models.Product;
import services.interfaces.IFilterService;

import java.util.List;
import java.util.Scanner;

public class FilterController implements IFilterController {
    private final IFilterService filterService;
    private final ICategoryController categoryController;
    private final Scanner scanner = new Scanner(System.in);

    public FilterController(IFilterService filterService, ICategoryController categoryController) {
        this.filterService = filterService;
        this.categoryController = categoryController;
    }

    @Override
    public void filterByCategory() {
        categoryController.getAllCategories();
        System.out.print("Choose category: ");
        int categoryId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\n----------");
        List<Product> productList = filterService.getProductsByCategory(categoryId);
        if (productList.isEmpty()) {
            System.out.println("The list of categories is empty.");
        } else {
            System.out.println("The list of categories is:");
            for (Product product : productList) {
                System.out.println(product);
            }
        }
        System.out.println("----------");
    }

    @Override
    public void filterByPriceRange() {
        System.out.print("Enter the minimum price: ");
        double minPrice = scanner.nextDouble();
        System.out.print("Enter the maximum price: ");
        double maxPrice = scanner.nextDouble();
        scanner.nextLine();

        List<Product> products =filterService.getProductsByPriceRange(minPrice, maxPrice);
        System.out.println("\n----------");
        if (products.isEmpty()) {
            System.out.println("The list is empty");
        } else {
            for (Product product : products) {
                System.out.println(product);
            }
        }
        System.out.println("----------");
    }

    @Override
    public void filterByQuantityRange() {
        System.out.print("Enter the minimum quantity: ");
        int minQuantity = scanner.nextInt();
        System.out.print("Enter the maximum quantity: ");
        int maxQuantity = scanner.nextInt();
        scanner.nextLine();
        List<Product> products = filterService.getProductsByQuantityRange(minQuantity, maxQuantity);
        System.out.println("\n----------");
        if (products.isEmpty()) {
            System.out.println("The list is empty");
        } else {
            for (Product product : products) {
                System.out.println(product);
            }
        }
        System.out.println("----------");
    }

    @Override
    public void filterByName() {
        System.out.print("Enter the name of the product (or part of it): ");
        String name = scanner.nextLine();
        List<Product> products =filterService.getProductsByName(name);
        System.out.println("\n----------");
        if (products.isEmpty()) {
            System.out.println("The list is empty");
        } else {
            for (Product product : products) {
                System.out.println(product);
            }
        }
        System.out.println("----------");
    }
}
