package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Warehouse {

    private List<Product> products;

    public Warehouse() {
        this.products = new ArrayList<>();
    }

    public Product findProduct(int id) {
        return products
                .stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public void removeProduct(int id) {
        products.removeIf(product -> product.getId() == id);
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

    public String formattedProducts() {
        StringBuilder response = new StringBuilder();
        for (Product product : products) {
            response.append(product).append("\n");
        }
        return response.toString();
    }

    public List<Product> getProducts() {
        return products;
    }

}