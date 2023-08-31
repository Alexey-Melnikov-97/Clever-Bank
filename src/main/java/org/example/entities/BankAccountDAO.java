package org.example.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankAccountDAO {
    private final Connection connection;

    public BankAccountDAO(Connection connection) {
        this.connection = connection;
    }

    public BankAccount getById(int id) throws SQLException {
        String query = "SELECT * FROM bank_account WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapRow(resultSet);
                }
            }
        }
        return null;
    }

    public BankAccount getByUserId(int id) throws SQLException {
        String query = "SELECT * FROM bank_account WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapRow(resultSet);
                }
            }
        }
        return null;
    }

    private BankAccount mapRow(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int userId = resultSet.getInt("user_id");
        int bankId = resultSet.getInt("bank_id");
        Double amount = resultSet.getDouble("amount");
        return new BankAccount(id, userId, bankId, amount);
    }

    public void update(BankAccount bankAccount) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE bank_account SET amount = ? WHERE id = ?");
            statement.setDouble(1, bankAccount.getAmount());
            statement.setInt(2, bankAccount.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

