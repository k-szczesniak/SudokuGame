package sudokupackage;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class FileSudokuBoardDao implements Dao<SudokuBoard> {

    private static final long serialVersionUID = 42L;

    private String fileName;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public FileSudokuBoardDao(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public SudokuBoard read() {
        SudokuBoard obj = null;
        try (FileInputStream fOut = new FileInputStream(fileName)) {
            ois = new ObjectInputStream(fOut);
            obj = (SudokuBoard) ois.readObject();
            return null;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public void write(SudokuBoard obj) {
        try (FileOutputStream fOut = new FileOutputStream(fileName)) {
            oos = new ObjectOutputStream(fOut);
            oos.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void finalize() throws Throwable {
        try {
            oos.close();
            ois.close();
        } finally {
            super.finalize();
        }
    }
}
