package model;

import java.sql.*;
import java.util.ArrayList;

public class SqlDataManager {
    private Connection connection;
    public SqlDataManager() {
        try {
            String url = "jdbc:mysql://localhost:3306/mydb";
            String dbUsername = "root";
            String dbPassword = "benis";
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addRegularUser(AccountRegular user) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("insert into beznyucet(jmeno, prijmeni, jmenoUctu, heslo) values('" +
                    user.getName() + "','" + user.getLastName() + "','" +
                    user.getUsername() + "','" + user.getPassword() + "')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addMessage(Message message) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("insert into zprava(druhZpravy, text, odesilatel) values('" +
                    message.getMessageType() + "','" + message.getMessage() + "','" +
                    message.getSender() + "')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //regular accounts WITHOUT transactions and sold coins
    public ArrayList<AccountRegular> getAllRegularAccounts() {
        ArrayList<AccountRegular> accounts = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from beznyucet");
            while (resultSet.next()) {
                AccountRegular acc = new AccountRegular(
                        resultSet.getInt("id"),
                        resultSet.getString("jmenoUctu"),
                        resultSet.getString("heslo"),
                        resultSet.getString("jmeno"),
                        resultSet.getString("prijmeni"));
                accounts.add(acc);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return accounts;
    }


    public boolean verifyCredentials(String username, String password) {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from beznyucet where jmenoUctu='"
                    + username + "' and heslo='" + password + "'");
            if (resultSet.next()) return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public void changePassword(String username, String newPassword) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("update beznyucet set heslo ='" + newPassword +
                    "' where jmenoUctu='" + username + "'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateProfile(String username, String name, String lastName) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("update beznyucet set jmeno ='" + name +
                    "', prijmeni='" + lastName + "' where jmenoUctu='" + username + "'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public AccountRegular getRegularUserByUsername(String username) {
        AccountRegular acc;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from beznyucet where jmenoUctu='"
                    + username + "'");
            if (resultSet.next()) {
                acc = new AccountRegular(
                        resultSet.getInt("id"),
                        resultSet.getString("jmenoUctu"),
                        resultSet.getString("heslo"),
                        resultSet.getString("jmeno"),
                        resultSet.getString("prijmeni"));
                return acc;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}