package models;

import java.util.ArrayList;
import java.util.List;


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

    public void updateProduct(int id, String name, int quantity, double price, int minQuantity) {
        for(Product product : products) {
            if (product.getId() == id){
                product.setName(name);
                product.setQuantity(quantity);
                product.setPrice(price);
                product.setMinQuantity(minQuantity);
                break;
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
}