package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.SqlDataManager;
import util.Util;

import java.util.Objects;

//todo Account balance, "my transactions", refactoring, implement todos.
//todo other account types
//todo Inbox
//todo don't forget to remove session testing in acc creation!
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        SqlDataManager.init();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader()
                .getResource("accountCreation.fxml")));
        primaryStage.setTitle("MENA Inzenyri DBMS Aplikace");
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        Util.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
