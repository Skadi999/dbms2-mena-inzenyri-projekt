package model;

public class AccountRegular {
    private int id;

    private String username;
    private String password;

    private String name;
    private String lastName;

    private String soldCoins;
    private String transactions;

    public AccountRegular(int id, String username, String password, String name, String lastName) {
        this(username, password, name, lastName);
        this.id = id;
    }

    public AccountRegular(String username, String password, String name, String lastName) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
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

    public String getSoldCoins() {
        return soldCoins;
    }

    public void setSoldCoins(String soldCoins) {
        this.soldCoins = soldCoins;
    }

    public String getTransactions() {
        return transactions;
    }

    public void setTransactions(String transactions) {
        this.transactions = transactions;
    }
}
