package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.Account;
import util.SqlDataManager;
import util.Util;

import java.util.List;

public class ManageAccounts {
    @FXML
    public ChoiceBox<String> cmbUsername;
    @FXML
    public TextField txtUsername;
    @FXML
    public TextField txtPassword;
    @FXML
    public TextField txtName;
    @FXML
    public TextField txtLastName;
    @FXML
    public Button btnDeleteAcc;
    @FXML
    public Button btnUpdateAccount;

    private final List<Account> accounts;
    private Account selectedAccount;

    public ManageAccounts() {
        accounts = SqlDataManager.getAllAccounts();
    }

    @FXML
    public void initialize() {
        for (Account acc : accounts) {
            cmbUsername.getItems().add(acc.getUsername());
        }
        //ChangeListener
        cmbUsername.valueProperty().addListener((ov, t, t1) -> updateFields());
    }

    private void updateFields() {
        if (findAccountByUsername() != null) {
            selectedAccount = findAccountByUsername();
            assert selectedAccount != null;
            txtUsername.setText(selectedAccount.getUsername());
            txtPassword.setText(selectedAccount.getPassword());
            txtName.setText(selectedAccount.getName());
            txtLastName.setText(selectedAccount.getLastName());
        }
    }

    @FXML
    private void updateAccountInfo() {
        SqlDataManager.updateAccount(txtUsername.getText(), txtPassword.getText(), txtName.getText(), txtLastName.getText());
        selectedAccount.setPassword(txtPassword.getText());
        selectedAccount.setName(txtName.getText());
        selectedAccount.setLastName(txtLastName.getText());
    }

    public void onDeleteAccount(ActionEvent actionEvent) {
        if (!cmbUsername.getValue().isBlank()) {
            SqlDataManager.deleteAccount(cmbUsername.getValue());
        }
        Util.switchToPage("manageAccounts");
    }

    private Account findAccountByUsername() {
        for (Account acc : accounts) {
            if (acc.getUsername().equals(cmbUsername.getValue())) {
                return acc;
            }
        }
        return null;
    }
}
