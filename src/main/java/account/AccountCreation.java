package account;

import enums.AccountType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Account;
import model.AccountRegular;
import model.SqlDataManager;
import util.Util;

import java.util.ArrayList;

public class AccountCreation {
    @FXML
    public Button btnRegister;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtConfirmPassword;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtLastName;

    private ArrayList<Account> accounts;

    @FXML
    public void initialize() {
        btnRegister.setDefaultButton(true);
    }

    public void onAccRegister(ActionEvent actionEvent) {
        accounts = SqlDataManager.getAllAccounts();
        if (isFieldEmpty()) {
            Util.alertError("Error", "All fields must be filled.");
        } else if (!isUsernameUnique()) {
            Util.alertError("Registration error", "Username is taken.");
        } else if (!verifyPassword()) {
            Util.alertError("Passwords do not match", "The values in New Password and Confirm Password" +
                    "must be the same!");
        } else {
            AccountRegular accRegular = new AccountRegular(txtUsername.getText(), txtPassword.getText(),
                    txtName.getText(), txtLastName.getText(), AccountType.REGULAR);
            SqlDataManager.addRegularUser(accRegular);
            Util.alertConfirmation("Success", "Account Created!");
        }
    }

    private boolean verifyPassword() {
        return txtPassword.getText().equals(txtConfirmPassword.getText());
    }

    private boolean isUsernameUnique() {
        for (Account acc : accounts) {
            if (txtUsername.getText().equals(acc.getUsername())) return false;
        }
        return true;
    }

    private boolean isFieldEmpty() {
        return txtUsername.getText().isBlank() || txtPassword.getText().isBlank()
                || txtConfirmPassword.getText().isBlank() || txtName.getText().isBlank() ||
                txtLastName.getText().isBlank();
    }
}
