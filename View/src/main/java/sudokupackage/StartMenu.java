package sudokupackage;

import java.io.IOException;
import java.util.HashSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

enum Levels {
    Easy, Medium, Hard;
}

public class StartMenu {

    private static Levels choice;

    @FXML
    ComboBox<String> comboBox;

    public static Levels getChoice() {
        return choice;
    }

    public void initialize() {
        comboBox.getItems().addAll(
                "Easy",
                "Medium",
                "Hard"
        );
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        try {
            String chosenLevel = comboBox.getSelectionModel().getSelectedItem().toString();
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
            stage.setScene(new Scene(root1, 500, 500));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
