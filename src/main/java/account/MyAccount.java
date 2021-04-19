package account;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Account;
import model.Session;
import model.SqlDataManager;
import util.Util;

import java.io.IOException;
import java.util.Objects;

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

    private final Account acc;

    public MyAccount() {
        acc = SqlDataManager.getAccountByUsername(Session.username);
    }

    //https://stackoverflow.com/questions/34785417/javafx-fxml-controller-constructor-vs-initialize-method
    @FXML
    public void initialize() {
        txtName.setText(acc.getName());
        txtLastName.setText(acc.getLastName());
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
}
