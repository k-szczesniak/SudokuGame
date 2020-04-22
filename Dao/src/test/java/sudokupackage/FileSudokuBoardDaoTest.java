package sudokupackage;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileSudokuBoardDaoTest {

    @Test
    public void writeAndReadTest() throws Exception {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard sudokuBoard2;
        Dao<SudokuBoard> fileSudokuBoardDao = SudokuBoardDaoFactory.getFileDao("SudokuBoardFile.txt");
        fileSudokuBoardDao.write(sudokuBoard);

        sudokuBoard2 = fileSudokuBoardDao.read();
        assertEquals(sudokuBoard,sudokuBoard2);
    }

    @Test
    void testReadException() {
        Dao<SudokuBoard> fileSudokuBoard = SudokuBoardDaoFactory.getFileDao("sudoku.txt");
        assertThrows(IOException.class, () -> {
            SudokuBoard sudoku = fileSudokuBoard.read();
        });
    }

    @Test
    public void testWriteException() {
        Dao<SudokuBoard> fileSudokuBoard = SudokuBoardDaoFactory.getFileDao("/sudoku:");
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        assertThrows(IOException.class, () -> {
            fileSudokuBoard.write(sudokuBoard);
        });
    }
}