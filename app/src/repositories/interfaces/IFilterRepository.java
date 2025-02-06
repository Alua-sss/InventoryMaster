package repositories.interfaces;

import models.Product;

import java.util.List;

public interface IFilterRepository {
    List<Product> getProductsByCategory(int categoryId);

    List<Product> getProductsByPriceRange(double minPrice, double maxPrice);

    List<Product> getProductsByQuantityRange(int minQuantity, int maxQuantity);

    List<Product> getProductsByName(String name);
}
