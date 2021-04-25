package model;

import enums.AccountType;

public class AccountRegular extends Account {
    private double balance;

    public AccountRegular(String username, String password, String name, String lastName, AccountType accountType,
                          double balance) {
        super(username, password, name, lastName, accountType);
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
