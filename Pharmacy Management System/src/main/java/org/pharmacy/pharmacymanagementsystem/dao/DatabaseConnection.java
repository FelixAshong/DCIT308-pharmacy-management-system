package org.pharmacy.pharmacymanagementsystem.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            return connection;
        } else {
            try {
                InputStream inputStream = DatabaseConnection.class.getClassLoader().getResourceAsStream("db.properties");
                Properties properties = new Properties();
                properties.load(inputStream);

                String url = properties.getProperty("db.url");
                String user = properties.getProperty("db.user");
                String password = properties.getProperty("db.password");

                connection = DriverManager.getConnection(url, user, password);
                return connection;
            } catch (IOException | SQLException e) {
                e.printStackTrace();
                throw new SQLException("Error connecting to the database");
            }
        }
    }
}
