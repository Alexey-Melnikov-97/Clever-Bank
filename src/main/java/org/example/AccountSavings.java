package org.example;

public interface AccountSavings {
    void deposit (double amount);
    void withdraw (double amount);
    void moneyOrder(UserAccount userAccount, double amount);
}
