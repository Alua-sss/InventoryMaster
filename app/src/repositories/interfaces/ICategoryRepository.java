package repositories.interfaces;

import models.Category;
import java.util.List;

public interface ICategoryRepository {
    boolean addCategory(String categoryName);
    Category getCategoryById(int id);
    List<Category> getAllCategories();
}

