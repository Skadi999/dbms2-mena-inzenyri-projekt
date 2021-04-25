package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import util.Session;
import util.Util;

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
        Util.switchToPage("myAccount");
    }

    public void onClickPageRegister(ActionEvent actionEvent) {
        Util.switchToPage("accountCreation");
    }

    public void onClickLoginPage(ActionEvent actionEvent) {
        Util.switchToPage("login");
    }

    public void onClickContactPage(ActionEvent actionEvent) {
        if (Session.username == null) {
            Util.alertError("Error", "Please log in to send a message.");
            return;
        }
        Util.switchToPage("contactUs");

    }

    public void onLogout(ActionEvent actionEvent) {
        if (Session.username == null) {
            Util.alertError("Error", "You are not logged in!");
            return;
        }
        Session.clear();
        Util.switchToPage("login");
    }

    public void onClickBrowseCoins(ActionEvent actionEvent) {
        Util.switchToPage("browseCoins");
    }

    public void onClickPageListCoin(ActionEvent actionEvent) {
        if (Session.username == null) {
            Util.alertError("Error", "You must be logged in.");
            return;
        }
        Util.switchToPage("listCoin");
    }


}
