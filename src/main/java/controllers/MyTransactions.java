package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Session;
import util.SqlDataManager;
import model.Transaction;

import java.util.List;

public class MyTransactions {
    @FXML
    public ListView<String> lvTransactions;

    private final List<Transaction> transactions;

    public MyTransactions() {
        transactions = SqlDataManager.getAllTransactionsByUsername(Session.username);
    }

    @FXML
    public void initialize() {
        for (Transaction transaction : transactions) {
            boolean isSeller = transaction.getSellerName().equals(Session.username);
            String soldOrBought = isSeller ? "SOLD\t" : "BOUGHT\t";
            lvTransactions.getItems().add(soldOrBought + transaction);
        }
    }
}