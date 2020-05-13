package sudokupackage;

import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sudokupackage.exceptions.StageException;

public class MainApp extends Application {

    private static final Logger logger = LoggerFactory.getLogger(MainApp.class);

    @Override
    public void start(Stage primaryStage) {
        ResourceBundle bundle = ResourceBundle.getBundle("Language");
        try {
            StageLoader.buildStage(primaryStage, "/startMenu.fxml","Menu", bundle);
        } catch (StageException e) {
            logger.error("Cannot load startMenu.fxml.");
            logger.debug("Cannot load startMenu.fxml.", e);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
