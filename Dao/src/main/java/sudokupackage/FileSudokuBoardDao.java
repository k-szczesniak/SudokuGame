git package sudokupackage;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {

//    private static Logger logger = LogManager.getLogger(String.valueOf(FileSudokuBoardDao.class));
    private final static Logger logger = Logger.getLogger(FileSudokuBoardDao.class);

    private static final long serialVersionUID = 42L;

    private String fileName;

    public FileSudokuBoardDao(String fileName) {
        this.fileName = fileName;
        PropertyConfigurator.configure("/home/student/IntelliJProject/" +
                "sudoku_git/pk-2020-mkwa-sr-10-05/View/src/main/resources/log4j.properties");
    }

    @Override
    public SudokuBoard read() throws Exception {
        try (FileInputStream fOut = new FileInputStream(fileName)) {
            ObjectInputStream ois = new ObjectInputStream(fOut);
            return (SudokuBoard) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            logger.debug("Debug log message");
            logger.info("Info log message");
            logger.error("Error log message");
            throw new IOException();
        }
    }

    @Override
    public void write(final SudokuBoard obj) throws Exception {
        try (FileOutputStream fOut = new FileOutputStream(fileName)) {
            ObjectOutputStream oos = new ObjectOutputStream(fOut);
            oos.writeObject(obj);
        } catch (IOException e) {
            throw new IOException();
        }
    }

    @Override
    public void close() throws Exception {

    }
}
