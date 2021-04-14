package app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Coin;
import model.SqlDataManager;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
//to fix misplaced labels either do some calculations with width
//or better create some layout for the labels (vbox??)
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
    public Button btnDisplayCoin;
    @FXML
    public ChoiceBox<Integer> cmbTestCoinChoice;

    @FXML
    public void initialize() {
        cmbTestCoinChoice.setValue(0);
    }

    public void onDisplayCoin(ActionEvent actionEvent) throws FileNotFoundException {
        Coin coin = SqlDataManager.getCoinById(cmbTestCoinChoice.getValue());
        if (coin == null) return;
        lblName.setText(coin.getName());
        lblPrice.setText(String.valueOf(coin.getPrice()));
        lblYear.setText(String.valueOf(coin.getYear()));
        lblCountry.setText(coin.getCountry());
        lblMetal.setText(coin.getMetal());
        FileInputStream inputstream = new FileInputStream(Coin.COIN_FOLDER_PATH + coin.getImagePath());
        Image img = new Image(inputstream);
        imgCoin.setImage(img);
    }
}
