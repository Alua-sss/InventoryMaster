package repositories;

import data.PostgresDB;
import data.interfaces.IDB;
import models.Category;
import repositories.interfaces.ICategoryRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository implements ICategoryRepository {
    IDB db = PostgresDB.getInstance();

    @Override
    public boolean addCategory(Category category) {
        Connection conn = null;
        try {
            conn = db.getConnection();
            if (conn == null) {
                System.out.println("Connection is null");
                return false;
            }

            String sql = "INSERT INTO categories (name) VALUES (?)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, category.getName());
            st.execute();

            return true;

        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Category getCategoryById(int id) {
        Connection conn = null;
        try {
            conn = db.getConnection();
            if (conn == null) {
                System.out.println("Connection is null");
                return null;
            }

            String sql = "SELECT * FROM categories WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Category(rs.getInt("id"), rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Category> getAllCategories() {
        Connection conn = null;
        try {
            conn = db.getConnection();
            if (conn == null) {
                System.out.println("Connection is null");
                return null;
            }
            List<Category> categories = new ArrayList<>();
            String sql = "SELECT * FROM categories";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                Category category = new Category( rs.getInt("id"),
                        rs.getString("name"));
                categories.add(category);
            }
            return categories;
        }catch (SQLException e){
            System.out.println("sql error: " + e.getMessage());
        }
        return null;
    }
}
