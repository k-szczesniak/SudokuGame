package sudokupackage;

import org.junit.jupiter.api.Test;
import sudokupackage.exceptions.DaoException;
import sudokupackage.exceptions.DatabaseDaoException;
import sudokupackage.exceptions.FileDaoException;

import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class JdbcSudokuBoardDaoTest {

    @Test
    public void writeAndReadTest() throws DaoException {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard sudokuBoard2 = null;
        Dao<SudokuBoard> jdbcSudokuBoardDao = SudokuBoardDaoFactory.getDatabaseDao("jdbcTestMethod");

        jdbcSudokuBoardDao.write(sudokuBoard);

        sudokuBoard2 = jdbcSudokuBoardDao.read();

        assertEquals(sudokuBoard,sudokuBoard2);
    }

    @Test
    void testReadException() {
        Dao<SudokuBoard> jdbcSudokuBoard = SudokuBoardDaoFactory.getDatabaseDao("sudoku.txt");
        assertThrows(DaoException.class, () -> {
            SudokuBoard sudoku = jdbcSudokuBoard.read();
        });
    }

    @Test
    public void testExceptionLanguage(){
        DatabaseDaoException e = new DatabaseDaoException("fileDaoExceptionMsgRead",new Throwable());
        ResourceBundle bundle =  ResourceBundle.getBundle("Language");
        String text = bundle.getString("fileDaoExceptionMsgRead");
        assertEquals(e.getLocalizedMessage(), text);
    }
}