package sudokupackage;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SudokuBoardView extends SudokuBoard {
    private boolean[][] isEditable = new boolean[9][9];

    public SudokuBoardView(SudokuSolver sudokusolver) {
        super(sudokusolver);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                isEditable[i][j] = true;
            }
        }
    }

    public boolean getIsEditable(int i, int j) {
        return isEditable[i][j];
    }

    public void setIsEditable(int i, int j, boolean editable) {
        isEditable[i][j] = editable;
    }

    public void calculateHiddenPostions(int counter) {
        Set<Integer> setOfPositions = new HashSet<Integer>();
        Random rand = new Random();
        while (counter > 0) {
            if (setOfPositions.add(rand.nextInt(81))) {
                counter--;
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (setOfPositions.contains(9 * i + j)) {
                    this.set(i, j, 0);
                }
            }
        }
    }

    public boolean checkBoard() {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.get(i, j) == 0) {
                    return false;
                }
            }
        }

        for (int i = 0; i < 9; i++) {
            if (!this.getRow(i).verify()) {
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (!this.getColumn(i).verify()) {
                return false;
            }
        }

        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                if (!this.getBox(i, j).verify()) {
                    return false;
                }
            }
        }
        return true;
    }

}
