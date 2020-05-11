package sudokupackage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sudokupackage.exceptions.LanguageException;
import sudokupackage.exceptions.LevelException;


public class StartMenu {

    private static final Logger logger = LoggerFactory.getLogger(StartMenu.class);

    private static Levels choice;
    public AnchorPane anchorChoice;
    private String language;

    @FXML
    ComboBox<String> comboBoxLevel;
    @FXML
    ComboBox<String> comboBoxLang;
    @FXML
    Button setLang;

    private ResourceBundle bundle = ResourceBundle.getBundle("Language");

    public static Levels getChoice() {
        return choice;
    }

    public void initialize() {
        comboBoxLang.getItems().addAll(
                bundle.getString("langEN"),
                bundle.getString("langPL")
        );

        comboBoxLevel.getItems().addAll(
                bundle.getString("lvlEasy"),
                bundle.getString("lvlMedium"),
                bundle.getString("lvlHard")
        );
    }

    private Locale changeLocal() {
        Locale locale = null;

        this.language = comboBoxLang.getSelectionModel().getSelectedItem().toString();
        if (language.equals(bundle.getString("langEN"))) {
            locale = new Locale("en");
            Locale.setDefault(locale);
        } else if (language.equals(bundle.getString("langPL"))) {
            locale = new Locale("pl");
            Locale.setDefault(locale);
        }

        return locale;
    }

    @FXML
    private void handleButtonSetLangAction(ActionEvent actionEvent) throws LanguageException {
        try {
            bundle = ResourceBundle.getBundle("Language", this.changeLocal());
            Parent pane = FXMLLoader.load(getClass()
                    .getResource("/startMenu.fxml"), bundle);
            anchorChoice.getChildren().setAll(pane);

        } catch (Exception e) {
            logger.error("Problems with language interface change.");
            throw new LanguageException(bundle.getString("languageExceptionMsg"), e);
        }
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws LevelException {
        try {
            String chosenLevel = comboBoxLevel.getSelectionModel().getSelectedItem().toString();
            choice = choice.valueOf(chosenLevel);
        } catch (NullPointerException e) {
            logger.warn("Level of difficulty not selected.");
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    bundle.getString("warnNotLevel"), ButtonType.OK);
            alert.show();
            throw new LevelException(bundle.getString("levelExceptionMsg"), e);
        }

        try {
            Parent root1;
            root1 = FXMLLoader.load(getClass().getClassLoader()
                    .getResource("gameWindow.fxml"), bundle);
            Stage stage = new Stage();
            stage.setTitle(bundle.getString("titleSudokuGameWindow"));
            Scene scene = new Scene(root1, 700, 700);
            scene.getStylesheets().add("style.css");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            logger.error("gameWindow.fxml not found");
        }
    }
}
