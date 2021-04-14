package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.SqlDataManager;

import java.util.Objects;

//todo styling
//todo browse coins, my transactions (history of sales), my listings, list item, buy item
//todo other account types
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        SqlDataManager.init();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("accountCreation.fxml")));
        primaryStage.setTitle("MENA Inzenyri DBMS Aplikace");
//        Scene scene = new Scene(root, 800, 600);
//        scene.getStylesheets().add("C:\\Users\\Skadi\\IdeaProjects\\dbms2project\\src\\main\\resources\\css\\styles.css");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
