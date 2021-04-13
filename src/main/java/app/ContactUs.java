package app;

import enums.MessageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import model.Message;
import model.Session;
import model.SqlDataManager;
import util.Util;

public class ContactUs {
    @FXML
    public ChoiceBox<String> chobxMsgType;
    @FXML
    public Button btnSubmit;
    @FXML
    public TextArea txtMessage;

    @FXML
    public void initialize() {
        btnSubmit.setDefaultButton(true);
    }

    public void onSubmitMessage(ActionEvent actionEvent) {
        if (Session.username == null) {
            Util.alertError("You must be logged in", "Please log in to send a message.");
            return;
        }
        if (txtMessage.getText().length() > 500) {
            Util.alertError("Cannot send message", "Your message length exceeds 500 characters!");
            return;
        }
        MessageType msgType = getMessageType(chobxMsgType.getValue());
        Message message = new Message(msgType.getNum(), txtMessage.getText(), Session.username);
        SqlDataManager.addMessage(message);
        Util.alertConfirmation("Success!", "Message Sent!");
    }

    private MessageType getMessageType(String messageType) {
        return switch (messageType) {
            default -> MessageType.TECHNICAL_ISSUE;
            case "Complaint" -> MessageType.COMPLAINT;
            case "Other" -> MessageType.OTHER;
        };

    }
}
