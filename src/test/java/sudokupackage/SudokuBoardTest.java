package sudokupackage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {
    @Test
    public void TestBoardRepeats() {
        SudokuBoard s1 = new SudokuBoard();
        SudokuBoard s2 = new SudokuBoard();

        s1.fillBoard();
        s2.fillBoard();
        boolean equal = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (s1.getBoard()[i][j] != s2.getBoard()[i][j]) {
                    equal = false;
                }
            }
        }
        assertEquals(equal, false);
    }

    @Test
    public void TestCorrectFillBoard() {
        SudokuBoard board = new SudokuBoard();
        board.fillBoard();

        //check row
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                for (int index = col + 1; index < 9; index++) {
                    if (board.getBoard()[row][col] == board.getBoard()[row][index]) {
                        fail("Row error"); //fail method from junit assertions
                    }
//                    assertNotEquals(board.getBoard()[row][col], board.getBoard()[row][index]);
                }
            }
        }

        //check column
        for (int col = 0; col < 9; col++) {
            for (int row = 0; row < 9; row++) {
                for (int index = row + 1; index < 9; index++) {
                    if (board.getBoard()[row][col] == board.getBoard()[index][col]) {
                        fail("Column error");
                    }
//                    assertNotEquals(board.getBoard()[row][col], board.getBoard()[index][col]);
                }
            }
        }

        //checkbox
//        for (int row = 0; row < 9; row += 3) {
//            for (int col = 0; col < 9; col += 3) {
//                for (int r = row; r < row + 3; r++) {
//                    for (int c = col; c < col + 3; c++) {
//                        if()
//                    }
//                    assertNotEquals(board.getBoard()[row][col], board.getBoard()[index][col]);
//                }
//            }
//        }
    }
}