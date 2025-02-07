package services;

import models.Product;
import repositories.interfaces.IProductRepository;
import services.interfaces.IProductService;

import java.util.List;

public class ProductService implements IProductService {

    private final IProductRepository repo;

    public ProductService(IProductRepository repo) {
        this.repo = repo;
    }


    @Override
    public List<Product> getAllProducts() {
        return repo.getAllProducts();
    }

    @Override
    public boolean addProduct(Product product) {

        if(product.getPrice() < 0 || product.getQuantity() < 0 ) {
            System.out.println("Incorrect price or quantity value");
            return false;
        }
        if ((product.getName() == null || product.getName().trim().isEmpty()) || product.getCategory() == null) {
            if (product.getName().length() < 3){
                System.out.println("Name of the product is less than 3 characters");
                return false;
            }
            System.out.println("Incorrect product name or category value");
            return false;
        }

        return repo.addProduct(product);
    }

    @Override
    public boolean deleteProduct(int id) {

        if (id < 1 ) {
            System.out.println("Incorrect value of ID");
            return false;
        }

        return repo.deleteProduct(id);
    }

    @Override
    public Product getProductById(int id) {

        if (id < 1 ) {
            System.out.println("Incorrect value of ID");
            return null;
        }
        return repo.getProductById(id);

    }

    @Override
    public boolean updateProduct(Product product) {

        if(product.getPrice() < 0 || product.getQuantity() < 0 ) {
            System.out.println("Incorrect price or quantity value");
            return false;
        }

        if ((product.getName() == null || product.getName().trim().isEmpty()) || product.getCategory() == null) {
            if (product.getName().length() < 3){
                System.out.println("Name of the product is less than 3 characters");
                return false;
            }
            System.out.println("Incorrect product name or category value");
            return false;
        }

        return repo.updateProduct(product);
    }
}
