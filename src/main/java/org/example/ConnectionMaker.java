package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMaker {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/clever";
        String username = "postgres";
        String password = "root";
        return DriverManager.getConnection(url, username, password);
    }
}
