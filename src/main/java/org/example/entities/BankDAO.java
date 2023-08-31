package org.example.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Bank> getAllBanks() throws SQLException {
        List<Bank> banks = new ArrayList<>();
        String query = "SELECT * FROM bank";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int bankId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                banks.add(new Bank(bankId, name));
            }
        }
        return banks;
    }

}
