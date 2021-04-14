package model;

import enums.AccountType;

public abstract class Account {
    private int id;

    private String username;
    private String password;

    private String name;
    private String lastName;

    private AccountType accountType;

    public Account(int id, String username, String password, String name, String lastName, AccountType accountType) {
        this(username, password, name, lastName, accountType);
        this.id = id;
    }

    public Account(String username, String password, String name, String lastName, AccountType accountType) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.accountType = accountType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
}
