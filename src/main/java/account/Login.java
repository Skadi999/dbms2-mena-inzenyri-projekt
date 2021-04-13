package account;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Session;
import model.SqlDataManager;
import util.Util;

public class Login {
    @FXML
    public TextField txtUsername;
    @FXML
    public TextField txtPassword;
    @FXML
    public Button btnLogin;

    public Login() {
        SqlDataManager.init();
    }

    @FXML
    public void initialize() {
        btnLogin.setDefaultButton(true);
    }

    public void onLogin(ActionEvent actionEvent) {
        if (Session.username != null) {
            Util.alertError("Login Error", "You are already logged in!");
            return;
        }
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        if (SqlDataManager.verifyCredentials(username, password)) {
            Util.alertConfirmation("Success!", "You have successfully logged in.");
            Session.username = username;
        } else {
            Util.alertError("Failure", "Wrong credentials entered.");
        }
    }
}
