package controllers;

import controllers.interfaces.IProductController;
import models.Category;
import models.Product;
import services.interfaces.ICategoryService;
import services.interfaces.IProductService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ProductController implements IProductController {

    private final IProductService productService;
    private final ICategoryService categoryService;

    private final Scanner scanner   = new Scanner(System.in);

    public ProductController(IProductService productService, ICategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public void addProduct() {
        try {
            System.out.println("\n----------");
            System.out.print("Enter product name: ");
            String name = scanner.nextLine();
            System.out.print("Enter product price: ");
            double price = scanner.nextDouble();
            System.out.print("Enter product quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine();


            //category надо бы как-то сократить или в отдельный метод положить
            System.out.print("\nChoose a category for the product: ");
            List<Category> categories = categoryService.getAllCategories();
            System.out.println("\n----------");
            if (categories.isEmpty()) {
                System.out.println("There are no categories. Add the category first.");
                return;
            }
            for (Category category : categories) {
                System.out.println(category.getId() + ": " + category.getName());
            }
            System.out.println("----------");

            System.out.print("\nEnter id category: ");
            int categoryId = scanner.nextInt();
            Category selectedCategory = categoryService.getCategoryById(categoryId);
            if (selectedCategory == null) {
                System.out.println("Category with such ID was not found.");
                return;
            }
            //сatefory


            Product product = new Product(name, price, quantity, selectedCategory);
            if (productService.addProduct(product)) {
                System.out.println("Product added");
                scanner.nextLine();

            }else{
                System.out.println("Error when adding a product.");
                scanner.nextLine();

            }
            System.out.println("----------");
        }

        catch (InputMismatchException e) {
            System.out.println("Please enter a valid value.");
            scanner.nextLine();
        }
    }

    @Override
    public void getProductById() {
        System.out.println("\n----------");
        System.out.print("Enter id: ");
        int id = scanner.nextInt();
        Product product = productService.getProductById(id);
        if (product != null) {
            System.out.println(product);
        } else {
            System.out.println("Error when getting product by id.");
        }
        System.out.println("----------");
    }

    @Override
    public void getAllProducts() {
        List<Product> products = productService.getAllProducts();
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
    public void updateProduct() {
        System.out.println("\n----------");
        System.out.print("Enter id: ");
        int id = scanner.nextInt();

        Product currentProduct = productService.getProductById(id);
        if (currentProduct == null) {
            System.out.println("Product not found");
            return;
        }

        while (true) {
            System.out.println("\n----------");
            System.out.println("What do you want to change?");
            System.out.println("1. Name");
            System.out.println("2. Quantity");
            System.out.println("3. Price");
            System.out.println("4. Save and back");
            System.out.print("Choose the action: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter product name: ");
                    String name = scanner.nextLine();
                    currentProduct.setName(name);
                    break;
                case 2:
                    System.out.println("Enter product quantity: ");
                    int quantity = scanner.nextInt();
                    currentProduct.setQuantity(quantity);
                    break;
                case 3:
                    System.out.println("Enter product price: ");
                    double price = scanner.nextDouble();
                    currentProduct.setPrice(price);
                    break;
                case 4:
                    productService.updateProduct(currentProduct);
                    return;
                default:
                    break;
            }
        }
    }
    @Override
    public void deleteProduct() {
        System.out.println("\n----------");
        System.out.print("Enter id: ");
        int id = scanner.nextInt();
        if(productService.deleteProduct(id)){
            System.out.println("Product is removed");
        }
        else{
            System.out.println("Error");
        }
        System.out.println("----------");
    }
}

