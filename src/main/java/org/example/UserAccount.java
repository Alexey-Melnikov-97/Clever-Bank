package org.example;

import lombok.*;

import java.util.ArrayList;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
public class UserAccount extends User implements AccountSavings {
    private static int realAccountNumber = 1;
    private String accountNumber;
    private double balance;
    private Bank bank;
    public UserAccount(Bank bank) {
        this.bank = bank;

        ArrayList<Integer> numberArray = new ArrayList<>();
        if (Integer.toString(realAccountNumber).length() < 8) {
            for (int i = 8; i > Integer.toString(realAccountNumber).length(); i--) {
                numberArray.add(0);
            }
            numberArray.add(realAccountNumber);
            realAccountNumber++;
            StringBuilder sb = new StringBuilder();
            for (Object o: numberArray) {
                sb.append(o);
            }
            accountNumber = sb.toString();
        } else accountNumber = Integer.toString(realAccountNumber);
    }

    public void deposit (double amount) {
        balance += amount;
        Receipt receipt = new Receipt();
        receipt.makeDepositWithdraw("Пополнение", this.getAccountNumber(), amount);
    }
    public void withdraw (double amount) {
        balance -= amount;
        Receipt receipt = new Receipt();
        receipt.makeDepositWithdraw("Снятие", this.getAccountNumber(), amount);
    }
    public void moneyOrder (UserAccount userAccount, double amount) {
        balance -= amount;
        userAccount.setBalance(userAccount.getBalance() + amount);
        Receipt receipt = new Receipt();
        if (getBank().equals(userAccount.getBank())) {
            receipt.makeMoneyOrder("Перевод", bank.getName(), userAccount.getBank().getName(),
                    this.getAccountNumber(), userAccount.getAccountNumber(), amount);
        } else {
            receipt.makeMoneyOrder("Перевод клиенту другого банка", bank.getName(), userAccount.getBank().getName(),
                    this.getAccountNumber(), userAccount.getAccountNumber(), amount);
        }
    }

    public static void main(String[] args) {
        UserAccount account1 = new UserAccount(new Bank("Sber"));
        account1.withdraw(1000);
    }
}
