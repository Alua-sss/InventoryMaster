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
        if(product.getPrice() < 0){
            System.out.println("Incorrect price value");
            if (product.getQuantity() < 0) {
                System.out.println("Incorrect price value");
                return false;
            }
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
        return repo.updateProduct(product);
    }
}
