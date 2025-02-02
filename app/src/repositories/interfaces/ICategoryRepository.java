package repositories.interfaces;

import models.Category;
import java.util.List;

public interface ICategoryRepository {
    boolean addCategory(Category category);
    Category getCategoryById(int id);
    List<Category> getAllCategories();
}

