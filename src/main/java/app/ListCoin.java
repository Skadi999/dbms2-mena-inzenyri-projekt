package app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import model.Coin;
import model.Session;
import model.SqlDataManager;
import util.Util;

import java.io.File;

public class ListCoin {
    @FXML
    public TextField txtName;
    @FXML
    public TextField txtMetal;
    @FXML
    public TextField txtCountry;
    @FXML
    public TextField txtYear;
    @FXML
    public TextField txtPrice;
    @FXML
    public TextField txtImagePath;
    @FXML
    public Button btnBrowse;
    @FXML
    public Button btnListCoin;

    private File selectedFile;

    public void onBrowse(ActionEvent actionEvent) {
        FileChooser directoryChooser = new FileChooser();
        selectedFile = directoryChooser.showOpenDialog(btnBrowse.getScene().getWindow());
        txtImagePath.setText(selectedFile.getAbsolutePath());
    }

    public void onListCoin(ActionEvent actionEvent) {
        if (isAFieldNull()) {
            Util.alertError("Error", "You must fill all the fields!");
        } else {
            String name = txtName.getText();
            double price = Double.parseDouble(txtPrice.getText());
            int year = Integer.parseInt(txtYear.getText());
            String country = txtCountry.getText();
            String metal = txtMetal.getText();
            String imagePath = selectedFile.getName();
            int accountId = SqlDataManager.getAccountIdByUsername(Session.username);

            Coin coin = new Coin(name, price, year, country, metal, imagePath, accountId);
            SqlDataManager.addCoin(coin);
            Util.alertConfirmation("Success!", "The coin has been listed.");
        }
    }

    private boolean isAFieldNull() {
        return txtName.getText().isBlank() || txtMetal.getText().isBlank() || txtCountry.getText().isBlank()
                || txtYear.getText().isBlank() || txtPrice.getText().isBlank() || txtImagePath.getText().isBlank();
    }
}