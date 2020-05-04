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
import javafx.stage.Stage;


public class StartMenu {

    private static Levels choice;
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

    @FXML
    private void handleButtonSetLangAction(ActionEvent actionEvent) throws IOException {
        try {
            this.language = comboBoxLang.getSelectionModel().getSelectedItem().toString();

            if (language.equals(bundle.getString("langEN"))) {
                Locale.setDefault(new Locale("en"));
            } else if (language.equals(bundle.getString("langPL"))) {
                Locale.setDefault(new Locale("pl"));
            }

            Parent root1;
            root1 = FXMLLoader.load(getClass().getClassLoader()
                    .getResource("startMenu.fxml"), bundle);
            Stage stage = new Stage();
            stage.setTitle(bundle.getString("title"));
            stage.setScene(new Scene(root1, 480, 350));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        try {
            String chosenLevel = comboBoxLevel.getSelectionModel().getSelectedItem().toString();
            choice = choice.valueOf(chosenLevel);
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "Please, choose level!", ButtonType.OK);
            alert.show();
        }

        try {
            Parent root1;
            root1 = FXMLLoader.load(getClass().getClassLoader().getResource("gameWindow.fxml"));
            Stage stage = new Stage();
            stage.setTitle("SudokuGame");
            Scene scene = new Scene(root1, 1000, 1000);
            scene.getStylesheets().add("style.css");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
