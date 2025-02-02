package repositories;

import data.PostgresDB;
import data.interfaces.IDB;
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

            String sql = "INSERT INTO products (name, quantity, price) VALUES (?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, product.getName());
            st.setInt(2,product.getQuantity());
            st.setDouble(3, product.getPrice());

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
            String sql = "SELECT * FROM products WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return new Product(rs.getInt("id"),rs.getString("name"),rs.getInt("quantity"),rs.getInt("price"));
            }
        }catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
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
            String sql = "SELECT * FROM products";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()){
                Product product = new Product( rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"));
                products.add(product);
            }
            return products;
        }catch (SQLException e){
            System.out.println("sql error: " + e.getMessage());
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
            String sql = "UPDATE products SET name = ?, quantity = ?, price = ? WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, product.getName());
            st.setInt(2, product.getQuantity());
            st.setDouble(3, product.getPrice());
            st.setInt(4, product.getId());
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
