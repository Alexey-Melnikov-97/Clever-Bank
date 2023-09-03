package org.example.entities;

import java.sql.*;

public class BankUserDAO {
    private final Connection connection;

    public BankUserDAO(Connection connection) {
        this.connection = connection;
    }

    public BankUser findById(int id) {
        BankUser bankUser = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM bank_user WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                bankUser = new BankUser(resultSet.getInt("id"), resultSet.getString("first_name"),
                        resultSet.getString("last_name"), resultSet.getString("email"));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bankUser;
    }
}
