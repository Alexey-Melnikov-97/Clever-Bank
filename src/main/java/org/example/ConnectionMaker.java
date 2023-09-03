package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Класс ConnectionMaker предоставляет средства для установления соединения с базой данных.
 */
public class ConnectionMaker {
    /**
     * Метод getConnection используется для получения объекта Connection, который представляет собой соединение с базой данных.
     *
     * @return объект Connection для соединения с базой данных.
     * @throws SQLException если произошла ошибка при установлении соединения.
     */
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "root";
        return DriverManager.getConnection(url, username, password);
    }
}
