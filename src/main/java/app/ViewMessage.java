package app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import model.Message;
import util.Util;

public class ViewMessage {
    @FXML
    public Label lblSender;
    @FXML
    public Button btnBack;
    @FXML
    public Label lblSubject;
    @FXML
    public TextArea txtMessage;

    private Message message;

    @FXML
    public void initialize() {
        message = Util.getActiveMessage();
        displayMessage();
    }

    private void displayMessage() {
        lblSender.setText(message.getSender());
        lblSubject.setText(message.getSubject() == null ? "No Subject" : message.getSubject());
        txtMessage.setText(message.getMessage());
        txtMessage.setEditable(false);
    }

    public void onGoBack(ActionEvent actionEvent) {
        Util.switchToPage("inbox");
    }
}
