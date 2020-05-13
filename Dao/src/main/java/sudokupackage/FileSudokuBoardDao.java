package sudokupackage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Locale;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sudokupackage.exceptions.DaoException;
import sudokupackage.exceptions.FileDaoException;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {

    private transient ResourceBundle bundle = ResourceBundle.getBundle("Language");

    private static final Logger logger = LoggerFactory.getLogger(FileSudokuBoardDao.class);

    private static final long serialVersionUID = 42L;

    private String fileName;

    public FileSudokuBoardDao(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public SudokuBoard read() throws FileDaoException {
        try (FileInputStream fOut = new FileInputStream(fileName)) {
            ObjectInputStream ois = new ObjectInputStream(fOut);
            return (SudokuBoard) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            logger.error("Exception during execute read() method.");
            logger.debug("Exception during execute read() method.", e);
            throw new FileDaoException(bundle.getString("fileDaoExceptionMsgRead"), e);
        }
    }

    @Override
    public void write(final SudokuBoard obj) throws FileDaoException {
        try (FileOutputStream fOut = new FileOutputStream(fileName)) {
            ObjectOutputStream oos = new ObjectOutputStream(fOut);
            oos.writeObject(obj);
        } catch (IOException e) {
            logger.error("Exception during execute write() method.");
            logger.debug("Exception during execute write() method.", e);
            throw new FileDaoException(bundle.getString("fileDaoExceptionMsgWrite"), e);
        }
    }

    @Override
    public void close() throws Exception {

    }
}
