package config.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionJDBC {
    public Connection getConnection() {
        String jdbcURL = "jdbc:mysql://localhost:3306/product_manager";
        String jdbcUsername = "root";
        String jdbcPassword = "mothaiba123.";
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver"); //connect vs driver của my sql
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
