package repositories;

import data.interfaces.IDB;
import models.User;
import repositories.interfaces.IUserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRepository implements IUserRepository  {
    private final IDB dp;

    public UserRepository(IDB dp) {
        this.dp = dp;
    }


    @Override
    public boolean registerUser(User user) {
        Connection conn = null;

        try {
            conn = dp.getConnection();
            if (conn == null) {
                System.out.println("Connection is null");
                return false;
            }
            String sql = "INSERT INTO users(username, password) VALUES (?, ?)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, user.getUsername());
            st.setString(2, user.getPassword());

            st.execute();

            return true;

        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public User getUser(String username) {
        return null;
    }
}
