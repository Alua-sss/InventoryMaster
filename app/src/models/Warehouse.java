package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Warehouse {
    private List<Product> products;

    public Warehouse() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public void removeProduct(int id) {
        products.removeIf(product -> product.getId() == id);
    }

    public void updateProduct() {
        System.out.print("Введите ID: ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        scanner.nextLine();
        Product currentProduct = null;

        for (Product product : products) {
            if (product.getId() == id) {
                currentProduct = product;
            }
        }
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


    public List<Product> getLowStockProducts() {
        List<Product> lowStockProducts = new ArrayList<>();
        for(Product product : products) {
            if (product.getQuantity() < product.getMinQuantity()) {
                lowStockProducts.add(product);
            }
        }
        return lowStockProducts;
    }

    public double calculateTotalValue() {
        double total = 0.0;
        for(Product product : products) {
            total += product.getQuantity() * product.getPrice();
        }
        return total;
    }

    public String getProducts() {
        StringBuilder response = new StringBuilder();
        for (Product product : products) {
            response.append(product).append("\n");
        }
        return response.toString();
    }
}