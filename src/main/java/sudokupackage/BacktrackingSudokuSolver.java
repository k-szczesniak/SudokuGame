package sudokupackage;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BacktrackingSudokuSolver implements SudokuSolver {

    @Override
    public void solve(final SudokuBoard sudoku) {

        Random rand = new Random();
        List<List<Integer>> randNumbers = Arrays.asList(new List[9]);
        for(int i=0; i<9; i++){
            randNumbers.set(i, Arrays.asList(new Integer[9]));
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                randNumbers.get(i).set(j, 0);
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                boolean safe = false;
                if (randNumbers.get(i).get(j) == 0) {
                    randNumbers.get(i).set(j, rand.nextInt(9) + 1);
                    int number = randNumbers.get(i).get(j);
                    do {
                        sudoku.set(i, j, number);
                        if (sudoku.get(i, j) != 0) {
                            safe = true;
                            break;
                        }
                        number = number % 9 + 1;
                    } while (number != randNumbers.get(i).get(j));
                } else {
                    int number = sudoku.get(i, j) % 9 + 1;
                    sudoku.set(i, j, number);
                    while (number != randNumbers.get(i).get(j)) {
                        if (sudoku.get(i, j) != 0) {
                            safe = true;
                            break;
                        }
                        number = number % 9 + 1;
                        sudoku.set(i, j, number);
                    }
                }
                if (!safe) {
                    randNumbers.get(i).set(j, 0);
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


