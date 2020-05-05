package sudokupackage;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javafx.beans.property.adapter.JavaBeanIntegerProperty;
import javafx.beans.property.adapter.JavaBeanIntegerPropertyBuilder;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;


public class GameWindow {
    @FXML
    private GridPane sudokuGrid;
    Levels choice;
    private JavaBeanIntegerProperty[][] integerProperty = new JavaBeanIntegerProperty[9][9];
    private TextField[][] field = new TextField[9][9];
    private SudokuBoard board;
    Random rand = new Random();
    private SudokuBoard boardToDisplay;

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
        fillSudokuGrid(boardToDisplay);
    }

    private void fillSudokuGrid(SudokuBoard boardToDisplay) throws NumberFormatException {
        sudokuGrid.setPrefSize(600, 600);
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
}
