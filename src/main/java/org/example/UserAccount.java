public class UserAccount extends User implements AccountSavings {
    private String accountNumber;
    private double balance;
    public UserAccount(){}
    public UserAccount (String firstName, String secondName, String lastName, String address, String phoneNumber,
                        String username, String password, String accountNumber, double balance) {
        super(firstName, secondName, lastName, address, phoneNumber, username, password);
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    //getters - setters
    public String getAccountNumber() {
        return this.accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public double getBalance() {
        return this.balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    //overrides
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAccount userAccount = (UserAccount) o;
        return getAccountNumber() != null ? !getAccountNumber().equals(userAccount.getAccountNumber()) : userAccount.getAccountNumber() != null;
    }

    //Операции с деньгами
    public void deposit (double amount) {
        balance += amount;
    }
    public void withdraw (double amount) {
        balance -= amount;
    }
    public void moneyOrder (double amount) {
        balance -= amount;
    }
}
