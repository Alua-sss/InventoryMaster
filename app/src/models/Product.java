package models;

public class Product {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private Category category;

    public Product(int id, String name, double price, int quantity, Category category) {
        setId(id);
        setName(name);
        setPrice(price);
        setQuantity(quantity);
        setCategory(category);
    }

    public Product(String name, double price, int quantity, Category category) {
        setName(name);
        setPrice(price);
        setQuantity(quantity);
        setCategory(category);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    @Override
    public String toString() {
        return "Product id=" + id + ", name=" + name + ", price=" + price + ", quantity=" + quantity;
    }
}
