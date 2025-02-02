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
    public boolean addCategory(Category category) {
        return repo.addCategory(category);
    }

    @Override
    public Category getCategoryById(int id) {
        Category category = repo.getCategoryById(id);
        return category;
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = repo.getAllCategories();
        return categories;
    }
}
