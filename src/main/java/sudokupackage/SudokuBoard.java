package sudokupackage;

import java.util.Arrays;
import java.util.Random;

public class SudokuBoard {

    private int[][] board = new int[9][9];

    public SudokuBoard() {
    }

    public SudokuBoard(final SudokuBoard b) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.board[i][j] = b.board[i][j];
            }
        }
    }

    private boolean isSafe(int row, int col) {

        for (int i = 0; i < row; i++) {
            if (board[row][col] == board[i][col]) {
                return false;
            }
        }
        for (int i = 0; i < col; i++) {
            if (board[row][col] == board[row][i]) {
                return false;
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int realRow = (row / 3) * 3 + i;
                int realCol = (col / 3) * 3 + j;
                if (board[realRow][realCol] == board[row][col] && (realRow * 9 + realCol) < row * 9 + col) {
                    return false;
                }
            }
        }
        return true;
    }

    public void fillBoard() {
        Random rand = new Random();
        int[][] randNumbers = new int[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                boolean safe = false;
                if (randNumbers[i][j] == 0) {
                    randNumbers[i][j] = rand.nextInt(9) + 1;
                    board[i][j] = randNumbers[i][j];
                    do {
                        if (isSafe(i, j)) {
                            safe = true;
                            break;
                        }
                        board[i][j] = nextNumber(i, j);
                    }
                    while (board[i][j] != randNumbers[i][j]);
                } else {
                    board[i][j] = nextNumber(i, j);
                    while (board[i][j] != randNumbers[i][j]) {
                        if (isSafe(i, j)) {
                            safe = true;
                            break;
                        }
                        board[i][j] = nextNumber(i, j);
                    }
                }
                if (!safe) {
                    randNumbers[i][j] = 0;
                    board[i][j] = 0;
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

    private int nextNumber(int i, int j) {
        return board[i][j] % 9 + 1;
    }

    public int[][] getCopyOfBoard() {
        int[][] copyOfboard = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                copyOfboard[i][j] = board[i][j];
            }
        }
        return copyOfboard;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SudokuBoard that = new SudokuBoard((SudokuBoard) o);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.board[i][j] != that.board[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

//    @Override
//    public int hashCode() {
//        return Arrays.hashCode(board);
//    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }
//
//    public boolean equals(final SudokuBoard object) {
//        boolean equal = true;
//        int[][] that = object.getCopyOfBoard();
//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 9; j++) {
//                if (this.board[i][j] != that[i][j]) {
//                    equal = false;
//                }
//            }
//        }
//        return equal;
//    }

}
