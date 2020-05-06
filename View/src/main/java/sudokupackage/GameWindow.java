package sudokupackage;

import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.property.adapter.JavaBeanIntegerProperty;
import javafx.beans.property.adapter.JavaBeanIntegerPropertyBuilder;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;


public class GameWindow {
    @FXML
    private GridPane sudokuGrid;
    Levels choice;
    private JavaBeanIntegerProperty[][] integerProperty = new JavaBeanIntegerProperty[9][9];
    private TextField[][] field = new TextField[9][9];
    private SudokuBoard board;
    private SudokuBoard boardToDisplay;
    private SudokuBoard originalGameBoard;
    Authors authors = new Authors();
    Random rand = new Random();

    public void initialize() throws CloneNotSupportedException {
        board = new SudokuBoard(new BacktrackingSudokuSolver());
        choice = StartMenu.getChoice();
        startSudoku();
    }

    private SudokuBoard calculateHiddenPostions(SudokuBoard board, int counter) {
        Set<Integer> setOfPositions = new HashSet<Integer>();
        while (counter > 0) {
            if (setOfPositions.add(rand.nextInt(81))) {
                counter--;
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (setOfPositions.contains(9 * i + j)) {
                    board.set(i, j, 0);
                }
            }
        }
        return board;
    }

    private void startSudoku() throws CloneNotSupportedException {
        board.solveGame();
        boardToDisplay = board.clone();
        boardToDisplay = calculateHiddenPostions(boardToDisplay, choice.getNumberOfcells());
        originalGameBoard = boardToDisplay.clone();
        fillSudokuGrid(boardToDisplay);
    }

    private void fillSudokuGrid(SudokuBoard boardToDisplay) throws NumberFormatException {
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
                    integerProperty[i][j] = builder.bean(boardToDisplay
                            .getSudokuField(i, j)).name("value")
                            .getter("getValue")
                            .setter("setValue")
                            .build();
                    field[i][j].textProperty()
                            .bindBidirectional(integerProperty[i][j], converter);

                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                if (boardToDisplay.get(i, j) != 0) {
                    field[i][j].setDisable(true);
                    field[i][j].setText(String.valueOf(boardToDisplay.get(i, j)));
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
            SudokuBoard boardFromFileOriginal = null;
            SudokuBoard boardFromFileEditable = null;
            Dao<SudokuBoard> fileSudokuBoardDaoOriginal = null;
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open file");
            String fileName = fileChooser.showOpenDialog(null).toString();

            if (fileName != null) {
                Dao<SudokuBoard> fileSudokuBoardDao = SudokuBoardDaoFactory.getFileDao(fileName);
                Pattern pattern = Pattern.compile("");
                Matcher matcher = pattern.matcher(fileName);
                if (matcher.find()) {
                    fileSudokuBoardDaoOriginal = SudokuBoardDaoFactory.getFileDao(fileName);
                } else {
                    fileSudokuBoardDaoOriginal = SudokuBoardDaoFactory
                            .getFileDao(fileName + "_original");
                }

                boardFromFileOriginal = fileSudokuBoardDaoOriginal.read();
                boardFromFileEditable = fileSudokuBoardDao.read();
            }

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    field[i][j].setDisable(false);
                    field[i][j].setText("");
                    integerProperty[i][j].setValue(boardFromFileOriginal.get(i, j));
                    if (integerProperty[i][j].getValue() != 0) {
                        field[i][j].setDisable(true);
                    }
                    integerProperty[i][j].setValue(boardFromFileEditable.get(i, j));
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
                Dao<SudokuBoard> fileSudokuBoardDaoOriginal =
                        SudokuBoardDaoFactory.getFileDao(fileName + "_original");
                fileSudokuBoardDao.write(boardToDisplay);
                fileSudokuBoardDaoOriginal.write(originalGameBoard);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleButtonShowAuthorsAction(ActionEvent actionEvent) throws IOException {
        try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    authors.getObject("1").toString() + " i "
                            + authors.getObject("2"), ButtonType.OK);
            alert.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
