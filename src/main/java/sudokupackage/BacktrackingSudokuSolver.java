package sudokupackage;

import java.util.Random;

public class BacktrackingSudokuSolver implements SudokuSolver {

    private boolean isSafe(int row, int col, final SudokuBoard sudoku) {

        for (int i = 0; i < row; i++) {
            if (sudoku.get(row, col) == sudoku.get(i, col)) {
                return false;
            }
        }
        for (int i = 0; i < col; i++) {
            if (sudoku.get(row, col) == sudoku.get(row, i)) {
                return false;
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int realRow = (row / 3) * 3 + i;
                int realCol = (col / 3) * 3 + j;
                if (sudoku.get(realRow, realCol) == sudoku.get(row, col)
                        && (realRow * 9 + realCol) < row * 9 + col) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void solve(final SudokuBoard sudoku) {

            Random rand = new Random();
            int[][] randNumbers = new int[9][9];

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    boolean safe = false;
                    if (randNumbers[i][j] == 0) {
                        randNumbers[i][j] = rand.nextInt(9) + 1;
                        sudoku.set(i, j, randNumbers[i][j]);
                        do {
                            if (isSafe(i, j, sudoku)) {
                                safe = true;
                                break;
                            }
                            sudoku.set(i, j,sudoku.get(i, j) % 9 + 1);
                        } while (sudoku.get(i, j) != randNumbers[i][j]);
                    } else {
                        sudoku.set(i, j,sudoku.get(i, j) % 9 + 1);
                        while (sudoku.get(i, j) != randNumbers[i][j]) {
                            if (isSafe(i, j, sudoku)) {
                                safe = true;
                                break;
                            }
                            sudoku.set(i, j,sudoku.get(i, j) % 9 + 1);
                        }
                    }
                    if (!safe) {
                        randNumbers[i][j] = 0;
                        sudoku.set(i, j, 0);
                        if (j == 0) {
                            j = 7;
                            i -= 1;
                        } else if (j == 1) {
                            i -= 1;
                            j = 8;
                        } else {
                            j -= 2;
                        }
                    }
                }
            }
    }
}
