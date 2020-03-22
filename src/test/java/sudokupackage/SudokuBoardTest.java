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
    public void TestCheckRows() {
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

}