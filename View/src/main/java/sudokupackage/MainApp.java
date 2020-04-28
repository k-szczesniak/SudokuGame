package sudokupackage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/startMenu.fxml"));

        primaryStage.setTitle("Choice Window");
        primaryStage.setScene(new Scene(root, 480, 350));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
