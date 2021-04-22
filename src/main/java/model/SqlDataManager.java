package model;

import enums.AccountType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
                    user.getUsername() + "','" + user.getBalance() + "')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void addMessage(Message message) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("insert into zprava(druhZpravy, text, odesilatel, prijemce, predmet) values('" +
                    message.getMessageType() + "','" + message.getMessage() + "','" +
                    message.getSender() + "','" + message.getReceiver() + "','" + message.getSubject() + "')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static List<Message> getMessagesByUsername(String username) {
        List<Message> messages = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from zprava where prijemce='" + username + "'");
            while (resultSet.next()) {
                Message message = new Message(resultSet.getInt("id"),
                        resultSet.getString("text"),
                        resultSet.getString("odesilatel"),
                        resultSet.getString("prijemce"),
                        resultSet.getString("predmet"));
                messages.add(message);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return messages;
    }

    public static Message getMessageById(int id) {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from zprava where id='" + id + "'");
            if (resultSet.next()) {
                return new Message(resultSet.getString("text"),
                        resultSet.getString("odesilatel"),
                        resultSet.getString("prijemce"),
                        resultSet.getString("predmet"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static void deleteMessageById(int id) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("delete from zprava where id='" + id + "'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static List<Message> getAllMessages() {
        List<Message> messages = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from zprava");
            while (resultSet.next()) {
                Message message = new Message(resultSet.getInt("id"),
                        resultSet.getInt("druhZpravy"),
                        resultSet.getString("text"),
                        resultSet.getString("odesilatel"),
                        resultSet.getString("predmet"));
                messages.add(message);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return messages;
    }

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

    public static void updateAccount(String username, String password, String name, String lastName) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("update ucet set jmeno ='" + name +
                    "', prijmeni='" + lastName + "', heslo='" + password + "' where jmenoUctu='" + username + "'");
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

    public static AccountRegular getRegularAccountByUsername(String username) {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from ucet where jmenoUctu='"
                    + username + "'");
            if (resultSet.next()) {
                return new AccountRegular(
                        resultSet.getString("jmenoUctu"),
                        resultSet.getString("heslo"),
                        resultSet.getString("jmeno"),
                        resultSet.getString("prijmeni"),
                        AccountType.REGULAR,
                        getAccountBalance(resultSet.getString("jmenoUctu")));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private static double getAccountBalance(String username) {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from beznyucet where jmenoUctu='"
                    + username + "'");
            if (resultSet.next()) {
                return resultSet.getDouble("zustatek");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public static void updateAccountBalance(String username, double newBalance) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("update beznyUcet set zustatek ='" + newBalance + "' where jmenoUctu='" + username + "'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteAccount(String username) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("delete from beznyucet where jmenoUctu='" + username + "'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try (Statement statement = connection.createStatement()) {
            statement.execute("delete from ucet where jmenoUctu='" + username + "'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
                        resultSet.getString("obrazekCesta"),
                        resultSet.getString("jmenoProdavajiciho"));
                return coin;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static List<Coin> getAllCoins() {
        List<Coin> coins = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from mince");
            while (resultSet.next()) {
                Coin coin = new Coin(resultSet.getInt("id"),
                        resultSet.getString("nazev"),
                        resultSet.getDouble("cena"),
                        resultSet.getInt("rok"),
                        resultSet.getString("zeme"),
                        resultSet.getString("kov"),
                        resultSet.getString("obrazekCesta"),
                        resultSet.getString("jmenoProdavajiciho"));
                coins.add(coin);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return coins;
    }

    public static List<Coin> getAllCoinsByUsername(String username) {
        List<Coin> coins = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from mince where jmenoProdavajiciho='"
                    + username + "'");
            while (resultSet.next()) {
                Coin coin = new Coin(resultSet.getInt("id"),
                        resultSet.getString("nazev"),
                        resultSet.getDouble("cena"),
                        resultSet.getInt("rok"),
                        resultSet.getString("zeme"),
                        resultSet.getString("kov"),
                        resultSet.getString("obrazekCesta"),
                        resultSet.getString("jmenoProdavajiciho"));
                coins.add(coin);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return coins;
    }

    public static void addCoin(Coin coin) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("insert into mince(cena, rok, nazev, kov, zeme, obrazekCesta, jmenoProdavajiciho) values('" +
                    coin.getPrice() + "','" + coin.getYear() + "','" +
                    coin.getName() + "','" + coin.getMetal() + "','" + coin.getCountry() + "','" +
                    coin.getImagePath() + "','" + coin.getSellerName() + "')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void removeCoin(int id) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("delete from mince where id='" + id + "'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void addTransaction(Transaction transaction) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("insert into transakce(idTransakce, nazevMince, cena, prodavajici, kupujici) " +
                    "values('" + transaction.getId() + "','" + transaction.getCoinName() + "','" +
                    transaction.getPrice() + "','" + transaction.getSellerName() + "','" + transaction.getBuyerName()
                    + "')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from transakce");
            while (resultSet.next()) {
                Transaction transaction = new Transaction(resultSet.getInt("idTransakce"),
                        resultSet.getString("nazevMince"), resultSet.getDouble("cena"),
                        resultSet.getString("prodavajici"), resultSet.getString("kupujici"));
                transactions.add(transaction);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return transactions;
    }

    private static Account getAccountByAccountType(ResultSet resultSet) throws SQLException {
        return switch (resultSet.getInt("typUctu")) {
            default -> getRegularAccountByUsername(resultSet.getString("jmenoUctu"));
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
