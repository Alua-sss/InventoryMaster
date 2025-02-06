package repositories.interfaces;

import models.Product;

import java.util.List;

public interface IProductRepository {

    boolean addProduct(Product product);

    Product getProductById(int id);
    List<Product> getAllProducts();

    boolean updateProduct(Product product);
    boolean deleteProduct(int id);

}
