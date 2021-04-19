package app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.Coin;
import model.SqlDataManager;
import util.Util;

import java.util.List;

public class BrowseCoins {
    @FXML
    public ListView<String> listCoins;
    @FXML
    public Button btnViewCoin;

    private final List<Coin> coins;

    public BrowseCoins() {
        coins = SqlDataManager.getAllCoins();
    }

    @FXML
    public void initialize() {
        for (Coin coin : coins) {
            listCoins.getItems().add(coin.toString());
        }
    }

    @FXML
    public void viewCoinPage(ActionEvent actionEvent) {
        String item = listCoins.getSelectionModel().getSelectedItem();
        int id = getItemId(item);
        Coin selectedCoin = SqlDataManager.getCoinById(id);
        Util.setActiveCoin(selectedCoin);
        Util.switchToPage("coin");
    }

    //this method of getting the ID is temporary.
    private int getItemId(String item) {
        String[] split = item.split(" ");
        String id = split[0].substring(1);
        return Integer.parseInt(id);
    }
}
