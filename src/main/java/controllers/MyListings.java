package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.Coin;
import model.Session;
import util.SqlDataManager;
import util.Util;

import java.util.List;

public class MyListings {
    @FXML
    public ListView<String> lvListings;
    @FXML
    public Button btnRemoveListing;

    private final List<Coin> coins;

    public MyListings() {
        coins = SqlDataManager.getAllCoinsByUsername(Session.username);
    }

    @FXML
    public void initialize() {
        for (Coin coin : coins) {
            lvListings.getItems().add(coin.toString());
        }
    }

    //this method of getting the ID is temporary.
    private int getItemId(String item) {
        String[] split = item.split(" ");
        String id = split[0].substring(1);
        return Integer.parseInt(id);
    }

    public void onClickRemoveListing(ActionEvent actionEvent) {
        String item = lvListings.getSelectionModel().getSelectedItem();
        int id = getItemId(item);
        SqlDataManager.removeCoin(id);
        Util.alertConfirmation("Success", "Listing has been removed.");
        Util.switchToPage("myAccount");
    }
}
