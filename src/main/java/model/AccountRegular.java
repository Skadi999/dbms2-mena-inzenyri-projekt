package model;

import enums.AccountType;

public class AccountRegular extends Account {
    private String listings;
    private String transactions;

    public AccountRegular(int id, String username, String password, String name, String lastName, AccountType accountType) {
        super(id, username, password, name, lastName, accountType);
    }

    public AccountRegular(String username, String password, String name, String lastName, AccountType accountType) {
        super(username, password, name, lastName, accountType);
    }


    public String getListings() {
        return listings;
    }

    public void setListings(String listings) {
        this.listings = listings;
    }

    public String getTransactions() {
        return transactions;
    }

    public void setTransactions(String transactions) {
        this.transactions = transactions;
    }
}
