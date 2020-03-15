package sudokupackage;
import java.util.Random;

public class SudokuBoard {

    private int[][] board = new int[9][9];

    private boolean isSafe(int position) {
        int row = position / 9;
        int col = position % 9;
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
        int squareRow = row / 3;
        int squareCol = col / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int realRow = squareRow * 3 + i;
                int realCol = squareCol * 3 + j;
                if (board[realRow][realCol] == board[row][col] && (realRow * 9 + realCol) < position) {
                    return false;
                }
            }
        }
        return true;
    }

    public void fillBoard() {
        Random rand = new Random();
        int[] randNumbers = new int[81];

        for (int i = 0; i < 81; i++) {
            boolean safe = false;
            int row = i / 9;
            int col = i % 9;
            if (randNumbers[i] == 0) {
                randNumbers[i] = rand.nextInt(9) + 1;
                board[row][col] = randNumbers[i];
                do {
                    if (isSafe(i)) {
                        safe = true;
                        break;
                    }
                    board[row][col] = board[row][col] % 9 + 1;
                }
                while (board[row][col] != randNumbers[i]);
            } else {
                board[row][col] = board[row][col] % 9 + 1;
                while (board[row][col] != randNumbers[i]) {
                    if (isSafe(i)) {
                        safe = true;
                        break;
                    }
                    board[row][col] = board[row][col] % 9 + 1;
                }
            }
            if (!safe) {
                randNumbers[i] = board[row][col]= 0;
                i -= 2;
            }
        }
    }

    public int[][] getBoard() {
        return board;
    }

    public void printBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
