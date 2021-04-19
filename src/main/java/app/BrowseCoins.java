package app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.Coin;
import model.SqlDataManager;
import util.Util;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

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
        //todo make coin page display this
        String item = listCoins.getSelectionModel().getSelectedItem();
        //this method of getting the ID is temporary.
        String[] split = item.split(" ");
        String id = split[0].substring(1);
        Coin selectedCoin = SqlDataManager.getCoinById(Integer.parseInt(id));

        Util.setActiveCoin(selectedCoin);
        switchToCoinPage();
    }

    private void switchToCoinPage() {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader()
                    .getResource("coin.fxml")));
            this.btnViewCoin.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
