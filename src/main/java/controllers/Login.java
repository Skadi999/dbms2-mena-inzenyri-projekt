package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import util.Session;
import util.SqlDataManager;
import util.Util;

public class Login {
    @FXML
    public TextField txtUsername;
    @FXML
    public TextField txtPassword;
    @FXML
    public Button btnLogin;

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
            Session.username = username;
            Util.switchToPage("myAccount");
        } else {
            Util.alertError("Failure", "Wrong credentials entered.");
        }
    }
}
