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
 * Класс `MoneyStatement` предоставляет методы для создания финансовых выписок для различных временных периодов с
 * информацией об исполненных операциях средств на банковском счете.
 */
public class MoneyStatement {
    /**
     * Выполняет расчет суммы денежных операций на основе переданного списка транзакций.
     * Возвращает массив, в котором первый элемент - сумма пополнений, а второй элемент - сумма снятий.
     *
     * @param transactionsList список транзакций
     * @return массив сумм пополнений и снятий
     */
    private double[] calculation(List<Transactions> transactionsList) {
        double plus = 0;
        double minus = 0;
        for (Transactions tr: transactionsList) {
            if (tr.getTransaction().equals("Deposit")) {
                plus += tr.getAmount();
            } else minus += tr.getAmount();
        }
        return new double[] {plus, minus};
    }
    /**
     * Генерирует финансовую выписку за последние 30 дней с информацией о пополнениях и снятиях с банковского счета.
     * Данный метод устанавливает соединение с базой данных, получает необходимую информацию о пользователе, банковом счете,
     * транзакциях и выполняет расчет сумм пополнений и снятий для указанного периода. Затем создает выписку с использованием
     * шаблонов и передает ее на обработку.
     */
    public void makeMoneyStatementMonth() {
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
            double[] sum = calculation(transactionsList);

            StatementPattern st = new StatementPattern();
            st.createMoneyStatement(name, accNumber, from, to, balance, sum[0], sum[1]);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Генерирует финансовую выписку за последний год с информацией о пополнениях и снятиях с банковского счета.
     * Данный метод устанавливает соединение с базой данных, получает необходимую информацию о пользователе, банковом счете,
     * транзакциях и выполняет расчет сумм пополнений и снятий для указанного периода. Затем создает выписку с использованием
     * шаблонов и передает ее на обработку.
     */
    public void makeMoneyStatementYear() {
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
            double[] sum = calculation(transactionsList);

            StatementPattern st = new StatementPattern();
            st.createMoneyStatement(name, accNumber, from, to, balance, sum[0], sum[1]);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Генерирует финансовую выписку за весь период с информацией о пополнениях и снятиях с банковского счета.
     * Данный метод устанавливает соединение с базой данных, получает необходимую информацию о пользователе, банковом счете,
     * транзакциях и выполняет расчет сумм пополнений и снятий для указанного периода. Затем создает выписку с использованием
     * шаблонов и передает ее на обработку.
     */
    public void makeMoneyStatementAllPeriod() {
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
            double[] sum = calculation(transactionsList);

            StatementPattern st = new StatementPattern();
            st.createMoneyStatement(name, accNumber, from, to, balance, sum[0], sum[1]);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
