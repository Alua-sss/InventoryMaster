package services;

import models.Category;
import repositories.interfaces.ICategoryRepository;
import repositories.interfaces.IProductRepository;
import services.interfaces.ICategoryService;

import java.util.List;

public class CategoryService implements ICategoryService {

    private final ICategoryRepository repo;

    public CategoryService(ICategoryRepository repo) {
        this.repo = repo;
    }

    @Override
    public boolean addCategory(String categoryName) {
        if( categoryName == null || categoryName.trim().isEmpty() ||categoryName.length() < 3) {
            System.out.println("Category name empty or less than 3 characters");
            return false;
        }
        return repo.addCategory(categoryName);
    }

    @Override
    public Category getCategoryById(int id) {
        if (id < 1 ) {
            System.out.println("Incorrect value of ID");
            return null;
        }
        Category category = repo.getCategoryById(id);
        return category;
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = repo.getAllCategories();
        return categories;
    }
}
