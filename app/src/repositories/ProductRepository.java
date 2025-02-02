package repositories;

import data.PostgresDB;
import data.interfaces.IDB;
import models.Category;
import models.Product;
import repositories.interfaces.IProductRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements IProductRepository {
    IDB db = PostgresDB.getInstance();

    @Override
    public boolean addProduct(Product product) {
        Connection conn = null;
        try {
            conn = db.getConnection();
            if (conn == null) {
                System.out.println("Connection is null");
                return false;
            }

            String sql = "INSERT INTO products (name, quantity, price, category_id) VALUES (?, ?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, product.getName());
            st.setInt(2,product.getQuantity());
            st.setDouble(3, product.getPrice());
            st.setInt(4, product.getCategory().getId());
            st.execute();

            return true;
        }catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
        return false;

    }

    @Override
    public Product getProductById(int id) {
        Connection conn = null;

        try {
            conn = db.getConnection();
            if (conn == null) {
                System.out.println("Connection is null");
                return null;
            }
            String sql = "SELECT p.*, c.name AS category_name FROM products p LEFT JOIN categories c ON p.category_id = c.id WHERE p.id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Category category = new Category(rs.getInt("category_id"), rs.getString("category_name"));
                return new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"), rs.getInt("quantity"), category);
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts() {

        Connection conn = null;

        try {
            conn = db.getConnection();
            if (conn == null) {
                System.out.println("Connection is null");
                return null;
            }
            List<Product> products = new ArrayList<>();
            String sql = "SELECT p.*, c.name AS category_name FROM products p LEFT JOIN categories c ON p.category_id = c.id";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Category category = new Category(rs.getInt("category_id"), rs.getString("category_name"));
                Product product = new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"), rs.getInt("quantity"), category);
                products.add(product);
            }
            return products;

        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean updateProduct(Product product) {
        Connection conn = null;
        try {
            conn = db.getConnection();
            if (conn == null) {
                System.out.println("Connection is null");
                return false;
            }
            String sql = "UPDATE products SET name = ?, quantity = ?, price = ?, category_id = ? WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, product.getName());
            st.setInt(2, product.getQuantity());
            st.setDouble(3, product.getPrice());
            st.setInt(4, product.getCategory().getId());
            st.setInt(5, product.getId());

            st.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteProduct(int id) {
        Connection conn = null;
        try {
            conn = db.getConnection();
            if (conn == null) {
                System.out.println("Connection is null");
                return false;
            }

            String sql = "DELETE FROM products WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setInt(1, id);
            st.executeUpdate();
            return true;

        }catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
        return false;
    }
}
