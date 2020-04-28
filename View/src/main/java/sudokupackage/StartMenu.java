package sudokupackage;

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

import java.io.IOException;

public class StartMenu {

    private String chosenLevel;
    @FXML
    ComboBox<String> comboBox;
    Button startGame;

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
            chosenLevel = comboBox.getSelectionModel().getSelectedItem().toString();
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Please, choose level!", ButtonType.OK);
            alert.show();
//            e.printStackTrace();
        }
        System.out.println(chosenLevel);
        try {
            Parent root1;
            root1 = FXMLLoader.load(getClass().getClassLoader().getResource("gameWindow.fxml"));
            Stage stage = new Stage();
            stage.setTitle("New window");
            stage.setScene(new Scene(root1, 450, 450));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
