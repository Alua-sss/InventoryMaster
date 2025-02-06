package services.interfaces;

import models.Category;

import java.util.List;

public interface ICategoryService {
    boolean addCategory(String categoryName);
    Category getCategoryById(int id);
    List<Category> getAllCategories();
}
