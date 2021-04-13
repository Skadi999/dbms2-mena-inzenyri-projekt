package account;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.AccountRegular;
import model.Session;
import model.SqlDataManager;
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

    private final AccountRegular acc;

    public MyAccount() {
        acc = SqlDataManager.getRegularUserByUsername(Session.username);
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
    }
}
