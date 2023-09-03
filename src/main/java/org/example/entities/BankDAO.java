package org.example.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankDAO {
    private final Connection connection;

    public BankDAO(Connection connection) {
        this.connection = connection;
    }

    public Bank getBankById(int id) throws SQLException {
        String query = "SELECT * FROM bank WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int bankId = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    return new Bank(bankId, name);
                }
            }
        }
        return null;
    }
}
