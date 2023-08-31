package org.example.entities;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionsDAO {
    private final Connection connection;

    public TransactionsDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Transactions transaction) throws SQLException {
        String query = "INSERT INTO transactions (id, datetime, transaction_type, amount) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, transaction.getId());
            statement.setObject(2, transaction.getDateTime());
            statement.setString(3, transaction.getTransaction());
            statement.setDouble(4, transaction.getAmount());
            statement.executeUpdate();
        }
    }

    public List<Transactions> getAll() throws SQLException {
        List<Transactions> transactionsList = new ArrayList<>();

        String query = "SELECT * FROM transactions";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                LocalDateTime dateTime = resultSet.getObject("date_time", LocalDateTime.class);
                String transaction = resultSet.getString("transaction");
                double amount = resultSet.getDouble("amount");

                Transactions transactions = new Transactions(id, dateTime, transaction, amount);
                transactionsList.add(transactions);
            }
        }

        return transactionsList;
    }

    public int getMaxId() throws SQLException {
        int id = 0;
        String query = "SELECT MAX(id) FROM transactions";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            if ((resultSet.next())) {
                if (resultSet.getObject(1) != null) {
                    id = resultSet.getInt(1);
                }
            }
        }
        return id;
    }
}
