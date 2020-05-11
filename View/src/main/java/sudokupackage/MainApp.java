package sudokupackage;

import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainApp extends Application {

    private static final Logger logger = LoggerFactory.getLogger(MainApp.class);

    @Override
    public void start(Stage primaryStage) throws Exception {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Language");
        try {
            Parent root = FXMLLoader.load(getClass()
                    .getResource("/startMenu.fxml"), resourceBundle);
            primaryStage.setTitle("Choice Window");
            primaryStage.setScene(new Scene(root, 480, 350));
            primaryStage.show();
        } catch (Exception e) {
            logger.error("startMenu.fxml not found.");
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
