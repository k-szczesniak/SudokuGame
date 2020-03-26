package sudokupackage;

import java.util.Random;

public class BacktrackingSudokuSolver implements SudokuSolver {

    @Override
    public void solve(final SudokuBoard sudoku) {

        Random rand = new Random();
        int[][] randNumbers = new int[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                boolean safe = false;
                if (randNumbers[i][j] == 0) {
                    randNumbers[i][j] = rand.nextInt(9) + 1;
                    int number = randNumbers[i][j];
                    do {
                        sudoku.set(i, j, number);
                        if (sudoku.get(i, j) != 0) {
                            safe = true;
                            break;
                        }
                        number = number % 9 + 1;
                    } while (number != randNumbers[i][j]);
                } else {
                    int number = sudoku.get(i, j) % 9 + 1;
                    sudoku.set(i, j, number);
                    while (number != randNumbers[i][j]) {
                        if (sudoku.get(i, j) != 0) {
                            safe = true;
                            break;
                        }
                        number = number % 9 + 1;
                        sudoku.set(i, j, number);
                    }
                }
                if (!safe) {
                    randNumbers[i][j] = 0;
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


