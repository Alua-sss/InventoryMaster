package services;

import models.Product;
import repositories.interfaces.IFilterRepository;
import services.interfaces.IFilterService;

import java.util.List;

public class FilterService implements IFilterService {

    private final IFilterRepository repo;

    public FilterService(IFilterRepository repo) {
        this.repo = repo;
    }


    @Override
    public List<Product> getProductsByCategory(int categoryId) {
        return repo.getProductsByCategory(categoryId);
    }

    @Override
    public List<Product> getProductsByPriceRange(double minPrice, double maxPrice) {
        return repo.getProductsByPriceRange(minPrice, maxPrice);
    }

    @Override
    public List<Product> getProductsByQuantityRange(int minQuantity, int maxQuantity) {
        return repo.getProductsByQuantityRange(minQuantity, maxQuantity);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return repo.getProductsByName(name);
    }
}
