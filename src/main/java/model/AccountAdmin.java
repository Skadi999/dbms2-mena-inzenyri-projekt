package model;

import enums.AccountType;

public class AccountAdmin extends Account {

    public AccountAdmin(String username, String password, String name, String lastName, AccountType accountType) {
        super(username, password, name, lastName, accountType);
    }
}
