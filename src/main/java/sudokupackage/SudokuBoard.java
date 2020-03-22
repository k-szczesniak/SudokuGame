package sudokupackage;

import java.util.HashSet;
import java.util.Set;

public class SudokuBoard {

    private int[][] board = new int[9][9];
    private SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();

    public int get(int x, int y) {
        return board[x][y];
    }

    public void set(int x, int y, int value) {
        this.board[x][y] = value;
    }

    private boolean checkRows() {
        for (int row = 0; row < 9; row++) {
            Set<Integer> set = new HashSet<Integer>();
            for (int col = 0; col < 9; col++) {
                if (!set.add(this.get(row, col))) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkColumns() {
        for (int col = 0; col < 9; col++) {
            Set<Integer> set = new HashSet<Integer>();
            for (int row = 0; row < 9; row++) {
                if (!set.add(this.get(row, col))) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkSquares() {
        for (int row = 0; row < 9; row += 3) {
            for (int col = 0; col < 9; col += 3) {
                Set<Integer> set = new HashSet<Integer>();
                for (int r = row; r < row + 3; r++) {
                    for (int c = col; c < col + 3; c++) {
                        if (!set.add(this.get(r, c))) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean checkBoard() {
        return this.checkRows()
                && this.checkColumns()
                && this.checkSquares();
    }

    public void solveGame() {
        sudokuSolver.solve(this);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SudokuBoard that = (SudokuBoard) o;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.board[i][j] != that.board[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
