package org.example.entities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<BankUser> findAll() {
        List<BankUser> bankUsers = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM bank_user");
            while (resultSet.next()) {
                BankUser bankUser = new BankUser(resultSet.getInt("id"), resultSet.getString("first_name"),
                        resultSet.getString("last_name"), resultSet.getString("email"));
                bankUsers.add(bankUser);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bankUsers;
    }

    public void save(BankUser bankUser) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO bank_users (id, first_name, last_name, email) VALUES (?, ?, ?, ?)");
            statement.setInt(1, bankUser.getId());
            statement.setString(2, bankUser.getFirstName());
            statement.setString(3, bankUser.getLastName());
            statement.setString(4, bankUser.getEmail());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(BankUser bankUser) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE bank_users SET first_name = ?, last_name = ?, email = ? WHERE id = ?");
            statement.setString(1, bankUser.getFirstName());
            statement.setString(2, bankUser.getLastName());
            statement.setString(3, bankUser.getEmail());
            statement.setInt(4, bankUser.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM bank_users WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
