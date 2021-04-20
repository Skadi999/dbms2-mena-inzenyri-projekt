package util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import model.Coin;
import model.Message;

import java.io.IOException;
import java.util.Objects;

public class Util {
    //this method of saving the selected coin is not good
    private static Coin activeCoin;
    private static Message activeMessage;

    private static Scene scene;

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

    //Specify the page name WITHOUT .fxml
    public static void switchToPage(String pageName) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(Util.class.getClassLoader()
                    .getResource(pageName + ".fxml")));
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Coin getActiveCoin() {
        return activeCoin;
    }

    public static void setActiveCoin(Coin activeCoin) {
        Util.activeCoin = activeCoin;
    }

    public static Message getActiveMessage() {
        return activeMessage;
    }

    public static void setActiveMessage(Message activeMessage) {
        Util.activeMessage = activeMessage;
    }

    public static void setScene(Scene scene) {
        Util.scene = scene;
    }
}
