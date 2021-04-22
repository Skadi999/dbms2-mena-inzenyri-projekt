package app;

import enums.MessageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Message;
import model.Session;
import model.SqlDataManager;
import util.Util;

public class ContactUs {
    @FXML
    public ChoiceBox<String> cmbMessageType;
    @FXML
    public Button btnSubmit;
    @FXML
    public TextArea txtMessage;
    @FXML
    public TextField txtSubject;

    @FXML
    public void initialize() {
        btnSubmit.setDefaultButton(true);
    }

    public void onSubmitMessage(ActionEvent actionEvent) {
        if (isAFieldEmpty()) {
            Util.alertError("Error", "All fields must be filled.");
            return;
        }
        if (txtMessage.getText().length() > 500) {
            Util.alertError("Cannot send message", "Your message length exceeds 500 characters!");
            return;
        }
        MessageType msgType = getMessageType(cmbMessageType.getValue());
        Message message = new Message(msgType.getNum(), txtMessage.getText(), Session.username, txtSubject.getText());
        SqlDataManager.addMessage(message);
        Util.alertConfirmation("Success!", "Message Sent!");
    }

    private MessageType getMessageType(String messageType) {
        return switch (messageType) {
            default -> MessageType.REGULAR;
            case "Technical Issue" -> MessageType.TECHNICAL_ISSUE;
            case "Complaint" -> MessageType.COMPLAINT;
            case "Other" -> MessageType.OTHER;
        };
    }

    private boolean isAFieldEmpty() {
        return txtMessage.getText().isBlank() || txtSubject.getText().isBlank();
    }
}
