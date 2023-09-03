package org.example;

import org.example.Receipts.MoneyStatement;
import org.example.Receipts.Statement;
import org.example.transactions.Deposit;
import org.example.transactions.Transfer;
import org.example.transactions.Withdraw;
import org.example.InterestCalculation.InterestCalculator;

import java.util.Scanner;

/**
 * Основной класс приложения.
 */
public class Main {
    /**
     * Точка входа в программу.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        // Запускаем калькулятор начисления процентов
        InterestCalculator calculator = new InterestCalculator();
        calculator.start();
        // Реализация консоли
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Выберете операцию, которую желаете выполнить:");
            System.out.println("1. Пополнение счета.");
            System.out.println("2. Снятие наличных.");
            System.out.println("3. Перевод средств другому клиенту.");
            System.out.println("4. Запросить выписку по транзакциям.");
            System.out.println("5. Запросить выписку о потраченных и полученных средствах.");
            System.out.println("6. Закрыть приложение.");

            int num = sc.nextInt();

            switch (num) {
                case 1 -> {
                    Deposit dep = new Deposit();
                    dep.makeDeposit();
                }
                case 2 -> {
                    Withdraw with = new Withdraw();
                    with.makeWithdraw();
                }
                case 3 -> {
                    Transfer tf = new Transfer();
                    tf.makeTransfer();
                }
                case 4 -> {
                    System.out.println("Выберите, за какой срок вы желаете получить выписку:");
                    System.out.println("1. Месяц.");
                    System.out.println("2. Год.");
                    System.out.println("3. Весь период обслуживания.");
                    int numb = sc.nextInt();
                    Statement st = new Statement();
                    switch (numb) {
                        case 1 -> st.makeStatementMonth();
                        case 2 -> st.makeStatementYear();
                        case 3 -> st.makeStatementAllPeriod();
                    }
                    System.out.println("Выписка сохранена в папку \"statement-money\"");
                }
                case 5 -> {
                    System.out.println("Выберите, за какой срок вы желаете получить выписку:");
                    System.out.println("1. Месяц.");
                    System.out.println("2. Год.");
                    System.out.println("3. Весь период обслуживания.");
                    int numb = sc.nextInt();
                    MoneyStatement st = new MoneyStatement();
                    switch (numb) {
                        case 1 -> st.makeMoneyStatementMonth();
                        case 2 -> st.makeMoneyStatementYear();
                        case 3 -> st.makeMoneyStatementAllPeriod();
                    }
                    System.out.println("Выписка сохранена в папку \"statement-money\"");
                }
                case 6 -> System.exit(0);
                default -> System.out.println("Такой операции нет. Выберите верный номер операции");

            }

        }
    }
}
