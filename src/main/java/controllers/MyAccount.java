package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.*;
import util.Session;
import util.SqlDataManager;
import util.Util;

public class MyAccount {
    @FXML
    public TextField txtOldPw;
    @FXML
    public TextField txtNewPw;
    @FXML
    public TextField txtConfirmNewPw;
    @FXML
    public Button btnChangePw;
    @FXML
    public TextField txtName;
    @FXML
    public TextField txtLastName;
    @FXML
    public Button btnUpdateProfile;
    @FXML
    public Button btnMyListings;
    @FXML
    public Button btnMyTransactions;
    @FXML
    public TextField txtBalance;
    @FXML
    public Button btnAddBalance;
    @FXML
    public Label lblBalance;
    @FXML
    public Button btnInbox;
    @FXML
    public Button btnSendMessage;
    @FXML
    public Button btnTickets;
    @FXML
    public Button btnManageAccounts;

    private final Account acc;
    public Pane paneBalance;
    private AccountRegular accRegular;
    private AccountSupport accSupport;
    private AccountAdmin accAdmin;

    public MyAccount() {
        acc = SqlDataManager.getAccountByUsername(Session.username);
        initAccountTypes();
    }

    //https://stackoverflow.com/questions/34785417/javafx-fxml-controller-constructor-vs-initialize-method
    @FXML
    public void initialize() {
        handleComponentsVisibility();
        txtName.setText(acc.getName());
        txtLastName.setText(acc.getLastName());
    }

    private void initAccountTypes() {
        if (acc instanceof AccountRegular) {
            accRegular = (AccountRegular) acc;
        } else if (acc instanceof AccountSupport) {
            accSupport = (AccountSupport) acc;
        } else {
            accAdmin = (AccountAdmin) acc;
        }
    }

    private void handleComponentsVisibility() {
        if (accSupport != null) {
            btnTickets.setVisible(true);
        }
        if (accAdmin == null) {
            btnManageAccounts.setVisible(false);
        }
        if (accRegular == null) {
            paneBalance.setVisible(false);
        } else {
            lblBalance.setText(String.valueOf(accRegular.getBalance()));
        }
    }

    public void changePassword(ActionEvent actionEvent) {
        if (!txtOldPw.getText().equals(acc.getPassword())) {
            Util.alertError("Wrong password", "The old password specified is not correct!");
        } else if (!verifyConfirmPassword()) {
            Util.alertError("Passwords do not match", "The values in New Password and Confirm Password" +
                    "must be the same!");
        } else {
            SqlDataManager.changePassword(acc.getUsername(), txtNewPw.getText());
            Util.alertConfirmation("Success!", "Password successfully changed.");
        }
    }

    private boolean verifyConfirmPassword() {
        return txtNewPw.getText().equals(txtConfirmNewPw.getText());
    }

    public void updateProfile(ActionEvent actionEvent) {
        SqlDataManager.updateProfile(Session.username, txtName.getText(), txtLastName.getText());
        Util.alertConfirmation("Success!", "Profile has been updated.");
    }

    public void onClickMyListings(ActionEvent actionEvent) {
        Util.switchToPage("myListings");
    }

    public void onClickMyTransactions(ActionEvent actionEvent) {
        Util.switchToPage("myTransactions");
    }

    public void onAddBalance(ActionEvent actionEvent) {
        if (accRegular == null) return;
        double addedBalance = Double.parseDouble(txtBalance.getText());
        SqlDataManager.updateAccountBalance(Session.username, accRegular.getBalance() + addedBalance);
        accRegular.setBalance(accRegular.getBalance() + addedBalance);
        lblBalance.setText(String.valueOf(accRegular.getBalance()));
    }

    public void onClickPageInbox(ActionEvent actionEvent) {
        Util.switchToPage("inbox");
    }

    public void onClickSendMessage(ActionEvent actionEvent) {
        Util.switchToPage("sendMessage");
    }

    public void onClickPageTickets(ActionEvent actionEvent) {
        Util.switchToPage("ticketReply");
    }

    public void onManageAccounts(ActionEvent actionEvent) {
        Util.switchToPage("manageAccounts");
    }
}