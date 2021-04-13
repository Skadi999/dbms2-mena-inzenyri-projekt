package account;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.AccountRegular;
import model.Session;
import model.SqlDataManager;
import util.Util;

import java.util.ArrayList;

public class AccountCreation {
    @FXML
    public Button btnRegister;
    //testing
    @FXML
    public Button btnTest;
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

    private ArrayList<AccountRegular> regularAccounts;

    @FXML
    public void initialize() {
        btnRegister.setDefaultButton(true);
    }

    public void onAccRegister(ActionEvent actionEvent) {
        regularAccounts = SqlDataManager.getAllRegularAccounts();
        if (!isUsernameUnique()) {
            Util.alertError("Registration error", "Username is taken.");
        } else if (!verifyPassword()) {
            Util.alertError("Passwords do not match", "The values in New Password and Confirm Password" +
                    "must be the same!");
        } else {
            AccountRegular accRegular = new AccountRegular(txtUsername.getText(), txtPassword.getText(),
                    txtName.getText(), txtLastName.getText());
            SqlDataManager.addRegularUser(accRegular);
        }
    }

    private boolean verifyPassword() {
        return txtPassword.getText().equals(txtConfirmPassword.getText());
    }

    private boolean isUsernameUnique() {
        for (AccountRegular acc : regularAccounts) {
            if (txtUsername.getText().equals(acc.getUsername())) return false;
        }
        return true;
    }

    //todo remove after testing
    public void testSession(ActionEvent actionEvent) {
        if (Session.username != null) {
            Util.alertConfirmation("session", Session.username);
        } else {
            Util.alertError("session", "Session is null!");
        }
    }
}
