package repositories;

import data.PostgresDB;
import data.interfaces.IDB;
import models.User;
import repositories.interfaces.IUserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository implements IUserRepository  {
    IDB db = PostgresDB.getInstance();

    @Override
    public boolean userExists(String username) {
        Connection conn = null;
        try {
            conn = db.getConnection();
            if (conn == null) {
                System.out.println("Connection is null");
                return false;
            }
            String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, username);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
        return false;
    }


    @Override
    public boolean registerUser(User user) {

        if (userExists(user.getUsername())) {
            return false;
        }

        Connection conn = null;

        try {

            conn = db.getConnection();

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
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT id,username,password,role FROM users WHERE username=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, username);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("role"));

            }
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return null;
    }
}
