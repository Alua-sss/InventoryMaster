package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Warehouse {

    private List<Product> products;

    public Warehouse() {
        this.products = new ArrayList<>();
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


}