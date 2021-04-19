package util;

import javafx.scene.control.Alert;
import model.Coin;

public class Util {
    //this method of saving the selected coin is not good
    private static Coin activeCoin;

    //alert without a headerText
    public static void alertError(String title, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public static void alertConfirmation(String title, String contentText) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setContentText(contentText);
        alert.show();
    }

    public static Coin getActiveCoin() {
        return activeCoin;
    }

    public static void setActiveCoin(Coin activeCoin) {
        Util.activeCoin = activeCoin;
    }
}
