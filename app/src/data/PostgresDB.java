package data;

import data.interfaces.IDB;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PostgresDB implements IDB {
    private String host;
    private String username;
    private String password;
    private String dbName;

    private static IDB instance;

    private Connection connection;

    private PostgresDB() {
        Properties props = loadProperties();

        setHost(props.getProperty("db.url"));
        setUsername(props.getProperty("db.user"));
        setPassword(props.getProperty("db.password"));
        setDbName(props.getProperty("db.name"));

    }

    private Properties loadProperties() {

        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("app/config/db.properties")) {
            props.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка загрузки конфигурации базы данных.");
        }
        return props;
    }

    public static PostgresDB getInstance() {
        if (instance == null) {
            instance = new PostgresDB();
        }
        return (PostgresDB) instance;
    }

    @Override
    public Connection getConnection() {
        String connectionUrl = host + "/" + dbName;
        try {
            if (connection != null && !connection.isClosed()) {
                return connection;
            }

            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection(connectionUrl, username, password);

            return connection;
        } catch (Exception e) {
            System.out.println("failed to connect to postgres: " + e.getMessage());
            return null;
        }
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Connection close error: " + ex.getMessage());
            }
        }
    }
}
