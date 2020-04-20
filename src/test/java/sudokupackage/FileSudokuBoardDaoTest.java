package sudokupackage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static sudokupackage.SudokuBoardDaoFactory.getFileDao;

class FileSudokuBoardDaoTest {

    @Test
    public void writeAndReadTest() {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard sudokuBoard2;
        Dao<SudokuBoard> fileSudokuBoardDao;

        fileSudokuBoardDao = getFileDao("Case1.txt");
        fileSudokuBoardDao.write(sudokuBoard);
        sudokuBoard2 = fileSudokuBoardDao.read(); // odczytywana instancja SudokuBoarda z pliku o okreslonej nazwie
        assertEquals(sudokuBoard,sudokuBoard2);
    }

    @Test
    void testFinalize() {
    }
}