package org.example.Receipts;

import org.example.entities.BankAccountDAO;
import org.example.entities.BankUserDAO;
import org.example.entities.Transactions;
import org.example.entities.TransactionsDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.ConnectionMaker.getConnection;

/**
 * Класс `Statement` предоставляет методы для создания банковских выписок для различных временных периодов.
 */
public class Statement {
    /**
     * Генерирует банковскую выписку за последние 30 дней.
     * Данный метод устанавливает соединение с базой данных, получает необходимую информацию о пользователе, банковом счете и транзакциях,
     * а затем создает выписку с использованием шаблонов и передает ее на обработку.
     */
    public void makeStatementMonth() {
        try (Connection connection = getConnection()) {

            BankUserDAO bankUserDAO = new BankUserDAO(connection);
            BankAccountDAO bankAccountDAO = new BankAccountDAO(connection);
            TransactionsDAO transactionsDAO = new TransactionsDAO(connection);
            LocalDateTime to = LocalDateTime.now();
            LocalDateTime from = to.minusDays(30);
            String name = bankUserDAO.findById(1).getFirstName() + " " + bankUserDAO.findById(1).getLastName();
            String accNumber = Integer.toString(bankAccountDAO.getByUserId(1).getId());
            Double balance = bankAccountDAO.getByUserId(1).getAmount();
            List<Transactions> allTransactionsList = transactionsDAO.getAll();
            List<Transactions> transactionsList = allTransactionsList.stream()
                    .filter(transaction -> transaction.getDateTime().isAfter(from) && transaction.getDateTime().isBefore(to))
                    .collect(Collectors.toList());

            StatementPattern st = new StatementPattern();
            st.createStatement(name, accNumber, from, to, balance, transactionsList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Генерирует банковскую выписку за последний год.
     * Данный метод устанавливает соединение с базой данных, получает необходимую информацию о пользователе, банковом счете и транзакциях,
     * а затем создает выписку с использованием шаблонов и передает ее на обработку.
     */
    public void makeStatementYear() {
        try (Connection connection = getConnection()) {

            BankUserDAO bankUserDAO = new BankUserDAO(connection);
            BankAccountDAO bankAccountDAO = new BankAccountDAO(connection);
            TransactionsDAO transactionsDAO = new TransactionsDAO(connection);
            LocalDateTime to = LocalDateTime.now();
            LocalDateTime from = to.minusYears(1);
            String name = bankUserDAO.findById(1).getFirstName() + " " + bankUserDAO.findById(1).getLastName();
            String accNumber = Integer.toString(bankAccountDAO.getByUserId(1).getId());
            Double balance = bankAccountDAO.getByUserId(1).getAmount();
            List<Transactions> allTransactionsList = transactionsDAO.getAll();
            List<Transactions> transactionsList = allTransactionsList.stream()
                    .filter(transaction -> transaction.getDateTime().isAfter(from) && transaction.getDateTime().isBefore(to))
                    .collect(Collectors.toList());

            StatementPattern st = new StatementPattern();
            st.createStatement(name, accNumber, from, to, balance, transactionsList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Генерирует банковскую выписку за весь период.
     * Данный метод устанавливает соединение с базой данных, получает необходимую информацию о пользователе, банковом счете и транзакциях,
     * а затем создает выписку с использованием шаблонов и передает ее на обработку.
     */
    public void makeStatementAllPeriod() {
        try (Connection connection = getConnection()) {

            BankUserDAO bankUserDAO = new BankUserDAO(connection);
            BankAccountDAO bankAccountDAO = new BankAccountDAO(connection);
            TransactionsDAO transactionsDAO = new TransactionsDAO(connection);
            LocalDateTime to = LocalDateTime.now();
            String name = bankUserDAO.findById(1).getFirstName() + " " + bankUserDAO.findById(1).getLastName();
            String accNumber = Integer.toString(bankAccountDAO.getByUserId(1).getId());
            Double balance = bankAccountDAO.getByUserId(1).getAmount();
            List<Transactions> allTransactionsList = transactionsDAO.getAll();
            List<Transactions> transactionsList = allTransactionsList.stream()
                    .filter(transaction -> transaction.getDateTime().isAfter(allTransactionsList.get(0).getDateTime()) && transaction.getDateTime().isBefore(to))
                    .collect(Collectors.toList());
            LocalDateTime from = transactionsList.get(0).getDateTime();

            StatementPattern st = new StatementPattern();
            st.createStatement(name, accNumber, from, to, balance, transactionsList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
