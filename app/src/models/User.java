package models;

public class User {
    private int id;
    private String username;
    private String password;
    private String role;

    public User(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    public User(String username, String password, String role) {
        setUsername(username);
        setPassword(password);
        setRole(role);
    }

    public User(int id,String username, String role) {
        setId(id);
        setUsername(username);
        setRole(role);
    }

    public User(int id, String username, String password, String role) {
        setId(id);
        setUsername(username);
        setPassword(password);
        setRole(role);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isAdmin() {
        return role.equalsIgnoreCase("Admin");
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}


