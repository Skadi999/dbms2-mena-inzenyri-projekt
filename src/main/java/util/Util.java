package util;

import javafx.scene.control.Alert;
public class Util {
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
    //for use in sql statements, removes the necessity to manually put single quotes
    //todo make use of it later
    public static String withQuotes(String text) {
        return "'" + text + "'";
    }
}
