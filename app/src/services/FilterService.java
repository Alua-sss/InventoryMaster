package services;

import models.Product;
import repositories.interfaces.IFilterRepository;
import services.interfaces.IFilterService;

import java.util.Collections;
import java.util.List;

public class FilterService implements IFilterService {

    private final IFilterRepository repo;

    public FilterService(IFilterRepository repo) {
        this.repo = repo;
    }


    @Override
    public List<Product> getProductsByCategory(int id) {
        if (id < 1 ) {
            System.out.println("Incorrect value of ID");
            return Collections.emptyList();
        }
        return repo.getProductsByCategory(id);
    }

    @Override
    public List<Product> getProductsByPriceRange(double minPrice, double maxPrice) {
        if (minPrice < 0 || minPrice > maxPrice) {
            System.out.println("Incorrect value of minPrice or maxPrice");
            return Collections.emptyList();
        }
        return repo.getProductsByPriceRange(minPrice, maxPrice);
    }

    @Override
    public List<Product> getProductsByQuantityRange(int minQuantity, int maxQuantity) {
        if (minQuantity < 0 || minQuantity > maxQuantity) {
            System.out.println("Incorrect value of minQuantity or maxQuantity");
            return Collections.emptyList();
        }
        return repo.getProductsByQuantityRange(minQuantity, maxQuantity);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Incorrect value of name");
            return Collections.emptyList();
        }
        return repo.getProductsByName(name);
    }
}
