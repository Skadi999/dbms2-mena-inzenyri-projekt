package util;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import model.Session;

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
    @FXML
    public Button btnBrowseCoins;
    @FXML
    public Button btnListCoin;

    public void onClickMyAccount(ActionEvent actionEvent) {
        if (Session.username == null) {
            Util.alertError("Cannot access My Account", "You must be logged in.");
            return;
        }
        switchToPage("myAccount");
    }

    public void onClickPageRegister(ActionEvent actionEvent) {
        switchToPage("accountCreation");
    }

    public void onClickLoginPage(ActionEvent actionEvent) {
        switchToPage("login");
    }

    public void onClickContactPage(ActionEvent actionEvent) {
        switchToPage("contactUs");
    }

    public void onLogout(ActionEvent actionEvent) {
        if (Session.username == null) {
            Util.alertError("Error", "You are not logged in!");
            return;
        }
        Session.clear();
        Util.alertConfirmation("Success!", "You have been logged out.");
        switchToPage("login");
    }

    public void onClickBrowseCoins(ActionEvent actionEvent) {
        switchToPage("browseCoins");
    }

    public void onClickPageListCoin(ActionEvent actionEvent) {
        if (Session.username == null) {
            Util.alertError("Error", "You must be logged in.");
            return;
        }
        switchToPage("listCoin");
    }

    //Specify the page name WITHOUT .fxml
    public void switchToPage(String pageName) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader()
                    .getResource(pageName + ".fxml")));
            this.btnPageLogin.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
