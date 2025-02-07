package repositories;

import data.PostgresDB;
import data.interfaces.IDB;
import models.Category;
import models.Product;
import repositories.interfaces.IFilterRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilterRepository implements IFilterRepository {

    IDB db = PostgresDB.getInstance();

    @Override
    public List<Product> getProductsByCategory(int categoryId) {
        String sql = "SELECT p.*, c.name AS category_name FROM products p " +
                "LEFT JOIN categories c ON p.category_id = c.id WHERE p.category_id = ?";
        return executeFilteredQuery(sql, categoryId);
    }

    @Override
    public List<Product> getProductsByPriceRange(double minPrice, double maxPrice) {
        String sql = "SELECT p.*, c.name AS category_name FROM products p " +
                "LEFT JOIN categories c ON p.category_id = c.id WHERE p.price BETWEEN ? AND ?";
        return executeFilteredQuery(sql, minPrice, maxPrice);
    }

    @Override
    public List<Product> getProductsByQuantityRange(int minQuantity, int maxQuantity) {
        String sql = "SELECT p.*, c.name AS category_name FROM products p " +
                "LEFT JOIN categories c ON p.category_id = c.id WHERE p.quantity BETWEEN ? AND ?";
        return executeFilteredQuery(sql, minQuantity, maxQuantity);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        String sql = "SELECT p.*, c.name AS category_name FROM products p " +
                "LEFT JOIN categories c ON p.category_id = c.id WHERE LOWER(p.name) LIKE LOWER(?)";
        return executeFilteredQuery(sql, "%" + name + "%");
    }


    private List<Product> executeFilteredQuery(String sql, Object... params) {
        Connection conn = null;
        try {
            conn = db.getConnection();
            if (conn == null) {
                System.out.println("Connection is null");
                return null;
            }

            List<Product> products = new ArrayList<>();
            PreparedStatement st = conn.prepareStatement(sql);

            for (int i = 0; i < params.length; i++) {
                st.setObject(i + 1, params[i]);
            }

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Category category = new Category(rs.getInt("category_id"), rs.getString("category_name"));
                    Product product = new Product(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getInt("quantity"),
                            category
                    );
                    products.add(product);
                }
            }
            return products;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }
}
