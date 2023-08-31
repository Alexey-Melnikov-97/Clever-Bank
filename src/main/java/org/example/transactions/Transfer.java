package org.example.transactions;

import org.example.ConnectionMaker;
import org.example.Receipt;
import org.example.entities.Bank;
import org.example.entities.BankAccount;
import org.example.entities.BankAccountDAO;
import org.example.entities.BankDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Transfer {

    public void makeTransfer() {
        Connection connection = null;

        try  {

            DecimalFormat formatter = new DecimalFormat("#0,00");

            // Устанавливаем соединение с БД
            connection = ConnectionMaker.getConnection();
            connection.setAutoCommit(false);

            // Создаем объект банковского счета пользователя
            BankAccountDAO badao = new BankAccountDAO(connection);
            BankAccount sendersAccount = new BankAccount(badao.getByUserId(1).getId(), badao.getByUserId(1).getUserId(),
                    badao.getByUserId(1).getBankId(), badao.getByUserId(1).getAmount());

            // Блокируем аккаунт пользователя
            PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM bank_account WHERE user_id = ? FOR UPDATE");
            selectStatement.setInt(1, sendersAccount.getId());

            //пишем сообщение о балансе Пользователя
            System.out.println("Баланс вашего лицевого счета составляет: " + sendersAccount.getAmount());

            //Спрашиваем номер счета (в нашем случае это Id) клиента, которому хотим перевести
            Scanner sc = new Scanner(System.in);
            System.out.println("Введите номер счета клиента, которому хотите совершить перевод: ");
            int payeesId = sc.nextInt();
            BankAccount payeesAccount = new BankAccount(badao.getById(payeesId).getId(), badao.getById(payeesId).getUserId(),
                    badao.getById(payeesId).getBankId(), badao.getById(payeesId).getAmount());

            // Узнаем сумму перевода
            System.out.println("Введите сумму для перевода: ");
            double amount = sc.nextDouble();

            //проверка наличия средств
            if (amount > sendersAccount.getAmount()) {
                System.out.println("Недостаточно средств на счете.");
                connection.rollback();
            }

            // Переводим деньги
            PreparedStatement updateSenderStatement = connection.prepareStatement("UPDATE bank_account SET amount = amount - ? WHERE id = ?");
            updateSenderStatement.setDouble(1, amount);
            updateSenderStatement.setInt(2, sendersAccount.getId());
            updateSenderStatement.executeUpdate();

            PreparedStatement updateReceiverStatement = connection.prepareStatement("UPDATE bank_account SET amount = amount + ? WHERE id = ?");
            updateReceiverStatement.setDouble(1, amount);
            updateReceiverStatement.setInt(2, payeesAccount.getId());
            updateReceiverStatement.executeUpdate();

            // Фиксируем транзакцию
            connection.commit();
            System.out.println("Операция выполнена успешно. Баланс вашего лицевого счета составляет: "
                    + badao.getByUserId(1).getAmount());

            // Делаем чек (замудрил)
            Receipt rcpt = new Receipt();
            BankDAO bdao = new BankDAO(connection);
            int sendersBankId = sendersAccount.getBankId();
            Bank sender = new Bank(sendersBankId, bdao.getBankById(sendersBankId).getName());
            int payeesBankId = payeesAccount.getBankId();
            Bank payee = new Bank(payeesBankId, bdao.getBankById(payeesBankId).getName());
            rcpt.makeMoneyOrder("Transfer", sender.getName(), payee.getName(),
            Integer.toString(sendersAccount.getId()), Integer.toString(payeesAccount.getId()), amount);

        } catch (SQLException e) {
            System.out.println("Подключение к базе данных не было выполнено. Повторите запрос позже.");
            throw new RuntimeException(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Нет соединения.");
            }
        }
    }
}
