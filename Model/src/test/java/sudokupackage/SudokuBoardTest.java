package sudokupackage;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {

    static final Logger logger = LoggerFactory.getLogger(SudokuBoardTest.class);

    @Test
    public void testGetterSetter() {
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
    public void testCheckBoardCheckSquare() {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.solveGame();
        for (int row = 0; row < 9; row += 3) {
            for (int col = 0; col < 9; col += 3) {
                Set<Integer> set = new HashSet<Integer>();
                for (int r = row; r < row + 3; r++) {
                    for (int c = col; c < col + 3; c++) {
                        if (!set.add(board.get(r, c))) {
                            logger.error("Error in checking squares");
                        }
                    }
                }
            }
        }
    }

    @Test
    public void testCheckBoardCheckRows() {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.solveGame();
        for (int row = 0; row < 9; row++) {
            Set<Integer> set = new HashSet<Integer>();
            for (int col = 0; col < 9; col++) {
                if (!set.add(board.get(row, col))) {
                    logger.error("Error in checking row");
                }
            }
        }
    }

    @Test
    public void testCheckBoardCheckColumns() {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.solveGame();
        for (int col = 0; col < 9; col++) {
            Set<Integer> set = new HashSet<Integer>();
            for (int row = 0; row < 9; row++) {
                if (!set.add(board.get(row, col))) {
                    logger.error("Error in checking column");
                }
            }
        }
    }

    @Test
    public void testBoardRepeats() {
        SudokuBoard s1 = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard s2 = new SudokuBoard(new BacktrackingSudokuSolver());

        s1.solveGame();
        s2.solveGame();

        assertEquals(s1.equals(s2), false);
    }

    @Test
    public void testEqualsMethod() {
        SudokuBoard s1 = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard s2 = s1;
        SudokuBoard s3 = null;
        SudokuBoard s4 = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuField sudokuField = new SudokuField(1);
        assertTrue(s1.equals(s2));
        assertFalse(s1.equals(s3));
        assertTrue(s1.equals(s4));
        assertFalse(s1.equals(sudokuField));
    }

    @Test
    public void testHashCode() {
        SudokuBoard s1 = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard s2 = s1;
        assertEquals(s1.hashCode(), s2.hashCode());
    }

    @Test
    public void testToString() {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        String result = "[[0, 0, 0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0]]";
        assertEquals(sudokuBoard.toString(), result);
    }

    @Test
    public void testClone() throws CloneNotSupportedException {
            SudokuBoard sudokuBoard1 = new SudokuBoard(new BacktrackingSudokuSolver());
            SudokuBoard sudokuBoard2 = sudokuBoard1.clone();
            assertTrue(sudokuBoard1.equals(sudokuBoard2));
            assertNotSame(sudokuBoard1,sudokuBoard2);
    }
}