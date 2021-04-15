package model;

import enums.AccountType;

import java.sql.*;
import java.util.ArrayList;

public class SqlDataManager {
    private static SqlDataManager sqlDataManager;
    private static Connection connection;

    private SqlDataManager() {
        try {
            String url = "jdbc:mysql://localhost:3306/mydb";
            String dbUsername = "root";
            String dbPassword = "benis";
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void init() {
        if (sqlDataManager == null) {
            sqlDataManager = new SqlDataManager();
        }
    }

    public static void addRegularUser(AccountRegular user) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("insert into ucet(jmeno, prijmeni, jmenoUctu, heslo, typUctu) values('" +
                    user.getName() + "','" + user.getLastName() + "','" +
                    user.getUsername() + "','" + user.getPassword() + "','" + user.getAccountType().getNum() + "')");

            statement.execute("insert into beznyucet(jmenoUctu) values('" +
                    user.getUsername() + "')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void addMessage(Message message) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("insert into zprava(druhZpravy, text, odesilatel) values('" +
                    message.getMessageType() + "','" + message.getMessage() + "','" +
                    message.getSender() + "')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //todo create separate method for choosing account based on type
    public static ArrayList<Account> getAllAccounts() {
        ArrayList<Account> accounts = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from ucet");
            while (resultSet.next()) {
                accounts.add(getAccountByAccountType(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return accounts;
    }


    public static boolean verifyCredentials(String username, String password) {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from ucet where jmenoUctu='"
                    + username + "' and heslo='" + password + "'");
            if (resultSet.next()) return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static void changePassword(String username, String newPassword) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("update ucet set heslo ='" + newPassword +
                    "' where jmenoUctu='" + username + "'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void updateProfile(String username, String name, String lastName) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("update ucet set jmeno ='" + name +
                    "', prijmeni='" + lastName + "' where jmenoUctu='" + username + "'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static Account getAccountByUsername(String username) {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from ucet where jmenoUctu='"
                    + username + "'");
            if (resultSet.next()) {
                return getAccountByAccountType(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static Coin getCoinById(int id) {
        Coin coin;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from mince where id='" + id + "'");
            if (resultSet.next()) {
                coin = new Coin(resultSet.getInt("id"),
                        resultSet.getString("nazev"),
                        resultSet.getDouble("cena"),
                        resultSet.getInt("rok"),
                        resultSet.getString("zeme"),
                        resultSet.getString("kov"),
                        resultSet.getString("obrazekCesta"));
                return coin;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private static Account getAccountByAccountType(ResultSet resultSet) throws SQLException {
        return switch (resultSet.getInt("typUctu")) {
            default -> new AccountRegular(
                    resultSet.getString("jmenoUctu"),
                    resultSet.getString("heslo"),
                    resultSet.getString("jmeno"),
                    resultSet.getString("prijmeni"),
                    AccountType.getType(resultSet.getInt("typUctu")));
            case 1 -> new AccountSupport(
                    resultSet.getString("jmenoUctu"),
                    resultSet.getString("heslo"),
                    resultSet.getString("jmeno"),
                    resultSet.getString("prijmeni"),
                    AccountType.getType(resultSet.getInt("typUctu")));
            case 2 -> new AccountAdmin(
                    resultSet.getString("jmenoUctu"),
                    resultSet.getString("heslo"),
                    resultSet.getString("jmeno"),
                    resultSet.getString("prijmeni"),
                    AccountType.getType(resultSet.getInt("typUctu")));
        };
    }
}
