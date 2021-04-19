package app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

//todo add receiver attribute to Message, have the Account be connected to Message using the id in Account as a FK
// to receiver ID in Message. When sending a message to support, think of something to use as a receiver ID.
// When replying, messagetype will be "TicketReply" or something like that.
public class TicketReply {
    public TextArea txtReply;
    public ChoiceBox<Integer> cmbTicketId;
    public Button btnTicketReply;

    @FXML
    public void initialize() {
        cmbTicketId.setValue(1);
    }

    public void onTicketReply(ActionEvent actionEvent) {

    }
}
