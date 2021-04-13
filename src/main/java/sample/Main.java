package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

//todo the database needs updates
//todo try to finish frontend, page to display coin attributes & img, etc.
//todo browse coins, my transactions, my listings, sell/buy
//todo other account types
//todo Try to limit the Session class to only one instance, clean up the code.
//todo try to make the sqldatamanger class methods static.
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("accountCreation.fxml")));
        primaryStage.setTitle("MENA Inzenyri DBMS Aplikace");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
