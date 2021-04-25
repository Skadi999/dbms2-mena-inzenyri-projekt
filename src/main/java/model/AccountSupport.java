package model;

import enums.AccountType;

public class AccountSupport extends Account {

    public AccountSupport(String username, String password, String name, String lastName, AccountType accountType) {
        super(username, password, name, lastName, accountType);
    }
}
