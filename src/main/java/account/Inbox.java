package account;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.Message;
import model.Session;
import model.SqlDataManager;
import util.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inbox {
    @FXML
    public ListView<String> lvInbox;
    @FXML
    public Button btnViewMessage;

    private final List<Message> messages;
    private Map<Integer, Message> idToMessage;
    private int index;

    public Inbox() {
        index = 0;
        idToMessage = new HashMap<>();
        messages = SqlDataManager.getMessagesByUsername(Session.username);
    }

    @FXML
    public void initialize() {
        for (Message message : messages) {
            lvInbox.getItems().add(message.toString());
            idToMessage.put(index, message);
            index++;
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
