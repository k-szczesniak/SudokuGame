package sudokupackage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardDaoFactoryTest {

    @Test
    public void getFileDao() {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        assertTrue(factory.getFileDao("SudokuBoardFile.txt") instanceof FileSudokuBoardDao);
    }
}