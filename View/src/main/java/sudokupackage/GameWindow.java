package sudokupackage;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.TextField;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class GameWindow {

    Levels choice;

    private SudokuBoard board;
    @FXML
    private GridPane sudokuGrid;
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
        SudokuBoard boardToDisplay = board.clone();
        boardToDisplay = calculateHiddenPostions(boardToDisplay, choice.getNumberOfcells());
        fillSudokuGrid(boardToDisplay);
    }

    private void fillSudokuGrid(SudokuBoard boardToDisplay) {
        sudokuGrid.setPrefSize(600, 600);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField field = new TextField();
                field.setMinSize(60, 60);
                field.setFont(Font.font(24));
                field.setAlignment(Pos.CENTER);
                field.setOpacity(1);
                field.setStyle("-fx-background-color: lightpink; -fx-cursor: hand; -fx-border-style: solid; " +
                        "-fx-border-color: grey; -fx-border-width: 3px; -fx-border-radius: 4px");
                if (boardToDisplay.get(i, j) != 0) {
                    field.setDisable(true);
                    field.setText(String.valueOf(boardToDisplay.get(i, j)));
                }
                sudokuGrid.add(field, i, j);
            }
        }
    }
}
