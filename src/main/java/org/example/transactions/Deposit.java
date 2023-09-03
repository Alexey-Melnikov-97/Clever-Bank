package org.example.transactions;

import org.example.entities.BankAccount;
import org.example.entities.BankAccountDAO;
import org.example.Receipts.DepositWithdrawTransaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import static org.example.ConnectionMaker.getConnection;

public class Deposit {
    public void makeDeposit() {

        // пытаемся установить соединение с БД
        try (Connection connection = getConnection()){

            // Создаем объект банковского счета Пользователя
            BankAccountDAO badao = new BankAccountDAO(connection);
            BankAccount account = new BankAccount(badao.getByUserId(1).getId(), badao.getByUserId(1).getUserId(),
                    badao.getByUserId(1).getBankId(), badao.getByUserId(1).getAmount());

            // Делаем пополнение
            System.out.println("Баланс вашего лицевого счета составляет: " + account.getAmount());
            Scanner sc = new Scanner(System.in);
            System.out.println("Введите сумму пополнения: ");
            double val = sc.nextDouble();
            account.setAmount(account.getAmount() + val);
            badao.update(account);
            System.out.println("Операция выполнена успешно. Баланс вашего лицевого счета составляет: "
                    + badao.getByUserId(1).getAmount());
            DepositWithdrawTransaction rcpt = new DepositWithdrawTransaction();
            rcpt.makeDepositWithdraw("Deposit", Integer.toString(account.getId()), val);

        } catch (
                SQLException e) {
            System.out.println("Подключение к базе данных не было выполнено. Повторите запрос позже.");
            throw new RuntimeException(e);
        }
    }
}
