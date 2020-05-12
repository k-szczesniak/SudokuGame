package sudokupackage;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sudokupackage.exceptions.LanguageException;
import sudokupackage.exceptions.LevelException;
import sudokupackage.exceptions.StageException;

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
            StageLoader.buildStage("/startMenu.fxml", bundle);
        } catch (StageException e) {
            logger.error("Problems with language interface change.");
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
            StageLoader.buildNewStage("/gameWindow.fxml", "style.css",
                    "titleSudokuGameWindow", bundle);
        } catch (StageException e) {
            logger.error("gameWindow.fxml not found");
        }
    }
}
