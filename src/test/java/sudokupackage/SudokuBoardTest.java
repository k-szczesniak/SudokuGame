package sudokupackage;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {

    @Test
    public void TestGetterSetter () {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        assertEquals(board.get(0, 0), 0);
        board.set(0, 0, 9);
        assertEquals(board.get(0, 0), 9);
        board.set(0, 1, 9);
        assertEquals(board.get(0, 1), 0);
        board.set(0, 1, 10);
        assertEquals(board.get(0, 1), 0);
    }

    @Test
    public void TestCheckBoardCheckSquare() {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.solveGame();
        for (int row = 0; row < 9; row += 3) {
            for (int col = 0; col < 9; col += 3) {
                Set<Integer> set = new HashSet<Integer>();
                for (int r = row; r < row + 3; r++) {
                    for (int c = col; c < col + 3; c++) {
                        if (!set.add(board.get(r, c))) {
                            fail("Box error");
                        }
                    }
                }
            }
        }
    }

    @Test
    public void TestCheckBoardCheckRows() {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.solveGame();
        for (int row = 0; row < 9; row++) {
            Set<Integer> set = new HashSet<Integer>();
            for (int col = 0; col < 9; col++) {
                if (!set.add(board.get(row, col))) {
                    fail("Row error");
                }
            }
        }
    }

    @Test
    public void TestCheckBoardCheckColumns() {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.solveGame();
        for (int col = 0; col < 9; col++) {
            Set<Integer> set = new HashSet<Integer>();
            for (int row = 0; row < 9; row++) {
                if (!set.add(board.get(row, col))) {
                    fail("Column error");
                }
            }
        }
    }

    @Test
    public void TestBoardRepeats() {
        SudokuBoard s1 = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard s2 = new SudokuBoard(new BacktrackingSudokuSolver());

        s1.solveGame();
        s2.solveGame();

        assertEquals(s1.equals(s2), false);
    }

    @Test
    public void TestEqualsMethod() {
        SudokuBoard s1 = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard s2 = s1;
        SudokuBoard s3 = null;
        assertTrue(s1.equals(s2));
        assertFalse(s1.equals(s3));
    }

    @Test
    public void TestHashCode() {
        SudokuBoard s1 = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard s2 = s1;
        assertEquals(s1.hashCode(),s2.hashCode());
    }
}