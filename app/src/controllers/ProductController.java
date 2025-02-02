package controllers;

import controllers.interfaces.IProductController;
import models.Product;
import services.interfaces.IProductService;
import java.util.List;
import java.util.Scanner;

public class ProductController implements IProductController {

    private final IProductService service;

    public ProductController(IProductService service) {
        this.service = service;
    }

    @Override
    public void addProduct() {
        System.out.println("\n*********************************************************************");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите название: ");
        String name = scanner.nextLine();
        System.out.print("Введите количество: ");
        int quantity = scanner.nextInt();
        System.out.print("Введите цену: ");
        double price = scanner.nextDouble();
        if (service.addProduct(new Product(name, price, quantity))) {
            System.out.println("Продукт был создан");
        } else {
            System.out.println("Ошибка");
        }
        System.out.println("*********************************************************************");
    }

    @Override
    public void getProductById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n*********************************************************************");
        System.out.print("Введите id: ");
        int id = scanner.nextInt();
        Product product = service.getProductById(id);
        if (product != null) {
            System.out.println("ID: " + product.getId() + " Название: " + product.getName() + " Цена: " + product.getPrice() + " Количество: " + product.getQuantity());
        } else {
            System.out.println("Ошибка");
        }
        System.out.println("*********************************************************************");
    }

    @Override
    public void getAllProducts() {
        List<Product> products;
        products = service.getAllProducts();
        System.out.println("\n*********************************************************************");
        if (!products.isEmpty()) {
            System.out.println("Список продуктов:");
            for (Product product : products) {
                System.out.println("ID: " + product.getId() + " Название: " + product.getName() + " Цена: " + product.getPrice() + " Количество: " + product.getQuantity());
            }
        } else {
            System.out.println("Список пуст");
        }
        System.out.println("*********************************************************************");
    }

    @Override
    public void updateProduct() {
        System.out.print("Введите ID: ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        scanner.nextLine();
        Product currentProduct = service.getProductById(id);
        service.updateProduct(currentProduct);

        if (currentProduct == null) {
            System.out.println("Товар с ID " + id + " не найден.");
            return;
        }

        while (true) {
            System.out.println("\nЧто вы хотите поменять?");
            System.out.println("1. Название");
            System.out.println("2. Количество товара");
            System.out.println("3. Цену");
            System.out.println("4. Сохранить и назад");
            System.out.print("Выберите опцию: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
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
                    service.updateProduct(currentProduct);
                    return;
                default:
                    break;
            }
        }
    }
    @Override
    public void deleteProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n*********************************************************************");
        System.out.print("Введите id: ");
        int id = scanner.nextInt();
        if(service.deleteProduct(id)){
            System.out.println("Товар был удален");
        }
        else{
            System.out.println("Ошибка");
        }
        System.out.println("*********************************************************************");
    }
}

