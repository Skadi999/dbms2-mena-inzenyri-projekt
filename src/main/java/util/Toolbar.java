package util;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import model.Session;
import sample.Main;

import java.io.IOException;
import java.util.Objects;

public class Toolbar {
    @FXML
    public Button btnMyAccount;
    @FXML
    public Button btnPageRegister;
    @FXML
    public Button btnPageLogin;
    @FXML
    public Button btnLogout;
    @FXML
    public Button btnContact;

    public void onClickMyAccount(ActionEvent actionEvent) {
        if (Session.username == null) {
            Util.alertError("Cannot access My Account", "You must be logged in.");
            return;
        }
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader()
                    .getResource("myAccount.fxml")));
            this.btnPageRegister.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onClickPageRegister(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader()
                    .getResource("accountCreation.fxml")));
            this.btnPageRegister.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onClickLoginPage(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("login.fxml")));
            this.btnPageLogin.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onClickContactPage(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("contactUs.fxml")));
            this.btnPageLogin.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onLogout(ActionEvent actionEvent) {
        Session.clear();
        Util.alertConfirmation("Success!", "You have logged out.");
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("login.fxml")));
            this.btnPageLogin.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
