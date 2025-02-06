package models;

import java.time.LocalDateTime;

public class StockTransaction {
    private int id;
    private Product product;
    private User user;
    private int quantity;
    private String transactionType; // "IN" (пополнение) или "OUT" (вычет)
    private LocalDateTime timestamp;

    public StockTransaction(int id, Product product, User user, int quantity, String transactionType, LocalDateTime timestamp) {
        setId(id);
        setProduct(product);
        setUser(user);
        setQuantity(quantity);
        setTransactionType(transactionType);
        setTimestamp(timestamp);
    }

    public StockTransaction(Product product, User user, int quantity, String transactionType) {
        setProduct(product);
        setUser(user);
        setQuantity(quantity);
        setTransactionType(transactionType);
        setTimestamp(LocalDateTime.now());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
