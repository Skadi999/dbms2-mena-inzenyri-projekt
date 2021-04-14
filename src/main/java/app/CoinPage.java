package app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.Coin;
import model.SqlDataManager;
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
    public Button btnDisplayCoin;
    @FXML
    public ChoiceBox<Integer> cmbTestCoinChoice;
    @FXML
    public Pane imageviewPane;

    @FXML
    public void initialize() {
        cmbTestCoinChoice.setValue(1);
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
        //todo note: this method of resizing the image might not be the best, maybe redo it later.
        Image img = new Image(inputstream, imageviewPane.getWidth(), imageviewPane.getHeight(), false, false);
//        imgCoin.setFitHeight(imageviewPane.getHeight());
//        imgCoin.setFitWidth(imageviewPane.getWidth());
        imgCoin.setImage(img);
    }
}
