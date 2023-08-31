package org.example;

import org.example.transactions.Deposit;
import org.example.transactions.Transfer;
import org.example.transactions.Withdraw;
import org.example.InterestCalculation.InterestCalculator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Запускаем калькулятор начисления процентов
        InterestCalculator calculator = new InterestCalculator();
        calculator.start();
        // Реализация консоли
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Выберете операцию, которую желаете выполнить:");
            System.out.println("1. Пополнение счета.");
            System.out.println("2. Снятие наличных");
            System.out.println("3. Перевод средств другому клиенту");
            System.out.println("4. Закрыть приложение");

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
                case 4 -> System.exit(0);
                default -> System.out.println("Такой операции нет. Выберите верный номер операции");

            }

        }
    }
}
