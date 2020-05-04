package sudokupackage;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;




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
                if (boardToDisplay.get(i, j) != 0) {
                    field.setDisable(true);
                    field.setText(String.valueOf(boardToDisplay.get(i, j)));
                }
                sudokuGrid.add(field, i, j);
            }
        }
    }
}
