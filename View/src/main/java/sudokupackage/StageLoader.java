package sudokupackage;

import java.io.IOException;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sudokupackage.exceptions.StageException;


public class StageLoader {

    private static Stage stage;

    private static Parent fxmlLoad(String fxmlPath, ResourceBundle bundle) throws IOException {
        return new FXMLLoader(StageLoader.class.getResource(fxmlPath), bundle).load();
    }

    public static void buildStage(String fxmlPath, ResourceBundle bundle) throws StageException {
        Parent parent;
        try {
            parent = fxmlLoad(fxmlPath, bundle);
        } catch (IOException e) {
            throw new StageException("Problems with reloading stage.", e);
        }
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public static void buildStage(Stage stage, String fxmlPath, String title,
                                  ResourceBundle bundle) throws StageException {
        StageLoader.stage = stage;
        Parent parent;
        try {
            parent = fxmlLoad(fxmlPath, bundle);
        } catch (IOException e) {
            throw new StageException("Problems with reloading stage.", e);
        }
        stage.setScene(new Scene(parent));
        stage.setTitle(title);
        stage.show();
    }

    public static void buildNewStage(String fxmlPath, String cssPath, String title,
                                     ResourceBundle bundle) throws StageException {
        Parent parent;
        try {
            parent = fxmlLoad(fxmlPath, bundle);
        } catch (IOException e) {
            throw new StageException("Problems with loading new stage.", e);
        }
        Stage newStage = new Stage();
        newStage.setTitle(bundle.getString(title));
        Scene scene = new Scene(parent);
        scene.getStylesheets().add(cssPath);
        newStage.setScene(scene);
        newStage.show();
    }

}
