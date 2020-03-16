package sudokupackage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.security.auth.login.FailedLoginException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {

    @Test
    public void TestCorrectFillBoard() {
        SudokuBoard board = new SudokuBoard();
        board.fillBoard();

        //check row
        for (int row = 0; row < 9; row++) {
            Set<Integer> set = new HashSet<Integer>();
            for (int col = 0; col < 9; col++) {
                if(set.add(board.getBoard()[row][col]) == false) {
                    fail("Row error");
                }
            }
        }

        //check column
        for (int col = 0; col < 9; col++) {
            Set<Integer> set = new HashSet<Integer>();
            for (int row = 0; row < 9; row++) {
                if(set.add(board.getBoard()[row][col]) == false) {
                    fail("Column error");
                }
            }
        }

        //check box 3x3
        for (int row = 0; row < 9; row += 3) {
            for (int col = 0; col < 9; col += 3) {
                Set<Integer> set = new HashSet<Integer>();
                for (int r = row; r < row + 3; r++) {
                    for (int c = col; c < col + 3; c++) {
                        if(set.add(board.getBoard()[r][c]) == false) {
                            fail("Box error");
                        }
                    }
                }
            }
        }
    }

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


}