package services.interfaces;

import models.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();
    boolean addProduct(Product product);
    boolean deleteProduct(int id);
    Product getProductById(int id);
    boolean updateProduct(Product product);
}
