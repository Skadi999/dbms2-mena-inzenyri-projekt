package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Message;
import util.Session;
import util.SqlDataManager;
import util.Util;

public class SendMessage {

    @FXML
    public TextArea txtMessage;
    @FXML
    public Button btnSubmit;
    @FXML
    public TextField txtReceiver;
    @FXML
    public TextField txtSubject;

    public void onSubmitMessage(ActionEvent actionEvent) {
        if (isAFieldEmpty()) {
            Util.alertError("Error", "All fields must be filled.");
            return;
        }
        Message message = new Message(txtMessage.getText(), Session.username, txtReceiver.getText(),
                txtSubject.getText());
        SqlDataManager.addMessage(message);
        Util.alertConfirmation("Success!", "Message sent!");
        clear();
    }

    private boolean isAFieldEmpty() {
        return txtMessage.getText().isBlank() || txtReceiver.getText().isBlank() || txtSubject.getText().isBlank();
    }

    private void clear() {
        txtReceiver.clear();
        txtMessage.clear();
        txtSubject.clear();
    }
}
