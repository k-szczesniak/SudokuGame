package sudokupackage;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {

    @Test
    public void TestCorrectFillBoardCheckRows() {
        SudokuBoard board = new SudokuBoard();
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(board);

        for (int row = 0; row < 9; row++) {
            Set<Integer> set = new HashSet<Integer>();
            for (int col = 0; col < 9; col++) {
                if (set.add(board.get(row, col)) == false) {
                    fail("Row error");
                }
            }
        }

    }

    @Test
    public void TestCorrectFillBoardCheckColumns() {
        SudokuBoard board = new SudokuBoard();
        board.fillBoard();

        for (int col = 0; col < 9; col++) {
            Set<Integer> set = new HashSet<Integer>();
            for (int row = 0; row < 9; row++) {
                if (set.add(board.getCopyOfBoard()[row][col]) == false) {
                    fail("Column error");
                }
            }
        }
    }

    @Test
    public void TestCorrectFillBoardCheckSquares() {
        SudokuBoard board = new SudokuBoard();
        board.fillBoard();
        for (int row = 0; row < 9; row += 3) {
            for (int col = 0; col < 9; col += 3) {
                Set<Integer> set = new HashSet<Integer>();
                for (int r = row; r < row + 3; r++) {
                    for (int c = col; c < col + 3; c++) {
                        if (set.add(board.getCopyOfBoard()[r][c]) == false) {
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

        assertEquals(s1.equals(s2), false);
    }

}