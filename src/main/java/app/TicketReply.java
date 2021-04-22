package app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import model.Message;
import model.Session;
import model.SqlDataManager;
import util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// When replying, messagetype will be "TicketReply" or something like that.
public class TicketReply {
    @FXML
    public TextArea txtReply;
    @FXML
    public ChoiceBox<Integer> cmbTicketId;
    @FXML
    public Button btnTicketReply;
    @FXML
    public Button btnView;

    private final List<Message> tickets;

    public TicketReply() {
        tickets = new ArrayList<>();
        for (Message message : SqlDataManager.getAllMessages()) {
            if (isSupportMessage(message.getMessageType())) {
                tickets.add(message);
            }
        }
    }

    @FXML
    public void initialize() {
        cmbTicketId.setValue(tickets.get(0).getId());
        for (Message ticket : tickets) {
            cmbTicketId.getItems().add(ticket.getId());
        }
    }

    public void onViewMessage(ActionEvent actionEvent) {
        Session.isViewingTicket = true;
        Util.setActiveMessage(findMessageById(cmbTicketId.getValue()));
        Util.switchToPage("viewMessage");
    }

    public void onTicketReply(ActionEvent actionEvent) {
        int msgId = cmbTicketId.getValue();
        String messageSender = Objects.requireNonNull(findMessageById(msgId)).getSender();
        String subject = Objects.requireNonNull(findMessageById(msgId)).getSubject();
        Message message = new Message(txtReply.getText(), "Support Team", messageSender,
                "Reply: " + subject);
        SqlDataManager.addMessage(message);
        Util.alertConfirmation("Success", "Message sent.");
        SqlDataManager.deleteMessageById(msgId);
        //experimental
        Util.switchToPage("ticketReply");
    }

    private boolean isSupportMessage(int messageType) {
        return messageType != -1;
    }

    private Message findMessageById(int id) {
        for (Message ticket : tickets) {
            if (ticket.getId() == id) return ticket;
        }
        return null;
    }
}
