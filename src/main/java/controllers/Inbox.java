package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.Message;
import util.Session;
import util.SqlDataManager;
import util.Util;

import java.util.List;

public class Inbox {
    @FXML
    public ListView<String> lvInbox;
    @FXML
    public Button btnViewMessage;

    private final List<Message> messages;

    public Inbox() {
        messages = SqlDataManager.getMessagesByUsername(Session.username);
    }

    @FXML
    public void initialize() {
        for (Message message : messages) {
            lvInbox.getItems().add(message.toString());
        }
    }

    public void onViewMessage(ActionEvent actionEvent) {
        String item = lvInbox.getSelectionModel().getSelectedItem();
        int id = getItemId(item);
        Message selectedMessage = SqlDataManager.getMessageById(id);
        Util.setActiveMessage(selectedMessage);
        Util.switchToPage("viewMessage");
    }

    //this method of getting the ID is temporary.
    private int getItemId(String item) {
        String[] split = item.split(" ");
        String id = split[0].substring(1);
        return Integer.parseInt(id);
    }
}
