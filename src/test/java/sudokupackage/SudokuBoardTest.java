package sudokupackage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {

    @Test
    public void TestGetterSetter () {
        SudokuBoard board = new SudokuBoard();
        assertEquals(board.get(0,0),0);
        board.set(0,0,9);
        assertEquals(board.get(0,0),9);
    }

    @Test
    public void TestCheckBoard() {
        SudokuBoard board = new SudokuBoard();
        board.solveGame();
        assertTrue(board.checkBoard());
    }

    @Test
    public void TestBoardRepeats() {
        SudokuBoard s1 = new SudokuBoard();
        SudokuBoard s2 = new SudokuBoard();

        s1.solveGame();
        s2.solveGame();

        assertEquals(s1.equals(s2), false);
    }

    @Test
    public void TestEqualsMethod() {
        SudokuBoard s1 = new SudokuBoard();
        SudokuBoard s2 = s1;
        SudokuBoard s3 = null;
        assertTrue(s1.equals(s2));
        assertFalse(s1.equals(s3));
    }

    @Test
    public void TestHashCode() {
        SudokuBoard s1 = new SudokuBoard();
        SudokuBoard s2 = s1;
        assertEquals(s1.hashCode(),s2.hashCode());
    }
}