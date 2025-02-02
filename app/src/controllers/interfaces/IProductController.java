package controllers.interfaces;

import models.Product;

import java.util.List;

public interface IProductController {
    void addProduct();
    void getProductById();
    void getAllProducts();
    void updateProduct();
    void deleteProduct();
}
