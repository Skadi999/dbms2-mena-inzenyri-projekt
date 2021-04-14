package model;

import enums.AccountType;

public class AccountSupport extends Account {

    public AccountSupport(int id, String username, String password, String name, String lastName, AccountType accountType) {
        super(id, username, password, name, lastName, accountType);
    }

    public AccountSupport(String username, String password, String name, String lastName, AccountType accountType) {
        super(username, password, name, lastName, accountType);
    }

    public void replyToTicket(int ticketId) {
        //todo
    }
}
