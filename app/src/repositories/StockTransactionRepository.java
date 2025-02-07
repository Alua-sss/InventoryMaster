package repositories;

import data.PostgresDB;
import data.interfaces.IDB;
import models.Category;
import models.Product;
import models.StockTransaction;
import models.User;
import repositories.interfaces.IStockTransactionRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockTransactionRepository implements IStockTransactionRepository {

    IDB db = PostgresDB.getInstance();

    @Override
    public boolean addTransaction(StockTransaction transaction) {
        Connection conn = null;
        try {
            conn = db.getConnection();
            if (conn == null) {
                System.out.println("Connection is null");
                return false;
            }
            String sql = "INSERT INTO stock_transactions (product_id, user_id, quantity, transaction_type, timestamp) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, transaction.getProduct().getId());
            st.setInt(2, transaction.getUser().getId());
            st.setInt(3, transaction.getQuantity());
            st.setString(4, transaction.getTransactionType());
            st.setTimestamp(5, Timestamp.valueOf(transaction.getTimestamp()));
            st.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
        return false;
    }


    @Override
    public List<StockTransaction> getFullStockTransactionDetails(int productId) {
        Connection conn = null;
        try {
            conn = db.getConnection();
            if (conn == null) {
                System.out.println("Connection is null");
                return null;
            }
            List<StockTransaction> transactions = new ArrayList<>();

            String sql = "SELECT st.id, st.quantity, st.transaction_type, st.timestamp, " +
                    "p.id AS product_id, p.name AS product_name, p.price, p.quantity AS product_quantity, " +
                    "c.id AS category_id, c.name AS category_name, " +
                    "u.id AS user_id, u.username AS user_name, u.role " +
                    "FROM stock_transactions st " +
                    "JOIN products p ON st.product_id = p.id " +
                    "JOIN categories c ON p.category_id = c.id " +
                    "JOIN users u ON st.user_id = u.id " +
                    "WHERE st.product_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, productId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category category = new Category(rs.getInt("category_id"), rs.getString("category_name"));
                Product product = new Product(
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getDouble("price"),
                        rs.getInt("product_quantity"),
                        category
                );
                User user = new User(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("role"));
                StockTransaction transaction = new StockTransaction(
                        rs.getInt("id"),
                        product,
                        user,
                        rs.getInt("quantity"),
                        rs.getString("transaction_type"),
                        rs.getTimestamp("timestamp").toLocalDateTime()
                );
                transactions.add(transaction);
            }
            return transactions;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
        return null;
    }
}
