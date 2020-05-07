package sudokupackage;

import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.beans.property.adapter.JavaBeanIntegerProperty;
import javafx.beans.property.adapter.JavaBeanIntegerPropertyBuilder;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;


public class GameWindow {

    @FXML
    private GridPane sudokuGrid;

    private Levels choice;
    private JavaBeanIntegerProperty[][] integerProperty = new JavaBeanIntegerProperty[9][9];
    private TextField[][] field = new TextField[9][9];
    private SudokuBoardView board;

    public void initialize() {
        board = new SudokuBoardView(new BacktrackingSudokuSolver());
        choice = StartMenu.getChoice();
        startSudoku();
    }

    private void startSudoku() {
        board.solveGame();
        board.calculateHiddenPostions(choice.getNumberOfcells());
        fillSudokuGrid();
    }

    private void fillSudokuGrid() throws NumberFormatException {
        StringConverter<Number> converter = new StringConverter<Number>() {

            @Override
            public String toString(Number object) {
                return object == null ? "" : object.toString();
            }

            @Override
            public Number fromString(String string) {
                if (string == null) {
                    return 0;
                } else {
                    try {
                        return Integer.parseInt(string);
                    } catch (NumberFormatException ex) {
                        return 0;
                    }
                }
            }

        };
        JavaBeanIntegerPropertyBuilder builder =
                JavaBeanIntegerPropertyBuilder.create();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                field[i][j] = new TextField();
                try {
                    integerProperty[i][j] = builder.bean(board
                            .getSudokuField(i, j)).name("value")
                            .getter("getValue")
                            .setter("setValue")
                            .build();
                    field[i][j].textProperty()
                            .bindBidirectional(integerProperty[i][j], converter);

                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                if (board.get(i, j) != 0) {
                    field[i][j].setDisable(true);
                    board.setIsEditable(i, j, false);
                    field[i][j].setText(String.valueOf(board.get(i, j)));
                } else {
                    field[i][j].setText("");
                }
                int finalI = i;
                int finalJ = j;
                field[i][j].textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String>
                                                observableValue, String s, String t1) {
                        if (!(t1.matches("[1-9]") || t1.matches(""))) {
                            field[finalI][finalJ].setText(s);
                        }
                    }
                });
                sudokuGrid.add(field[i][j], j, i);
            }
        }
    }

    @FXML
    private void handleButtonOpenAction(ActionEvent actionEvent) throws IOException {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open file");
            String fileName = fileChooser.showOpenDialog(null).toString();

            if (fileName != null) {
                Dao<SudokuBoard> fileSudokuBoardDao = SudokuBoardDaoFactory.getFileDao(fileName);
                board = (SudokuBoardView) fileSudokuBoardDao.read();
            }

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    field[i][j].setDisable(false);
                    field[i][j].setText("");
                    integerProperty[i][j].setValue(board.get(i, j));
                    if(!board.getIsEditable(i, j)){
                        field[i][j].setDisable(true);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleButtonSaveAction(ActionEvent actionEvent) throws IOException {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save file");
            String fileName = fileChooser.showSaveDialog(null).toString();

            if (fileName != null) {
                Dao<SudokuBoard> fileSudokuBoardDao = SudokuBoardDaoFactory.getFileDao(fileName);
                fileSudokuBoardDao.write(board);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleButtonShowAuthorsAction(ActionEvent actionEvent) throws IOException {
        ResourceBundle authors = ResourceBundle.getBundle("sudokupackage.Authors");
        try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    authors.getObject("1").toString() + ", "
                            + authors.getObject("2"), ButtonType.OK);
            alert.setHeaderText(null);
            alert.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}