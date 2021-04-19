package app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.Coin;
import model.SqlDataManager;
import util.Util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CoinPage {
    @FXML
    public Label lblName;
    @FXML
    public Label lblPrice;
    @FXML
    public Label lblYear;
    @FXML
    public Label lblCountry;
    @FXML
    public Label lblMetal;
    @FXML
    public ImageView imgCoin;
    @FXML
    public Pane imageviewPane;
    @FXML
    public Button btnBuyCoin;

    private Coin coin;

    @FXML
    public void initialize() throws FileNotFoundException {
        coin = Util.getActiveCoin();
        displayCoin();
    }

    public void displayCoin() throws FileNotFoundException {
        if (coin == null) return;
        lblName.setText(coin.getName());
        lblPrice.setText("$" + coin.getPrice());
        lblYear.setText(String.valueOf(coin.getYear()));
        lblCountry.setText(coin.getCountry());
        lblMetal.setText(coin.getMetal());

        FileInputStream inputstream = new FileInputStream(Coin.COIN_FOLDER_PATH + coin.getImagePath());
        Image img = new Image(inputstream, imageviewPane.getPrefWidth(), imageviewPane.getPrefHeight(), false, false);
        imgCoin.setImage(img);
    }

    public void onBuyCoin(ActionEvent actionEvent) {
        //todo condition of having enough balance here and deduction of balance
        Util.alertConfirmation("Success!", "Coin bought! $" + coin.getPrice() +
                " has been deducted from your account balance.");
        SqlDataManager.removeCoin(coin.getId());
        coin = null;
        Util.switchToPage("browseCoins");
    }
}
