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
        List<Product> products = repo.getAllProducts();
        return products;
    }

    @Override
    public boolean addProduct(Product product) {
        if(product.getPrice() < 0){
            System.out.println("Некорректное значение цены");
            if (product.getQuantity() < 0) {
                System.out.println("Некорректное значение цены");
                return false;
            }
            return false;
        }
        return repo.addProduct(product);
    }

    @Override
    public boolean deleteProduct(int id) {
        if (id < 1 ) {
            System.out.println("Некорректное значение id");
            return false;
        }
       return repo.deleteProduct(id);
    }

    @Override
    public Product getProductById(int id) {
        if (id < 1 ) {
            System.out.println("Некорректное значение id");
            return null;
        }
        return repo.getProductById(id);
    }

    @Override
    public boolean updateProduct(Product product) {
        repo.updateProduct(product);
        return true;
    }
}
