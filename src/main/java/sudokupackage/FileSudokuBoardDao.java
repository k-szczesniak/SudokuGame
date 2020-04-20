package sudokupackage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {

    private static final long serialVersionUID = 42L;

    private String fileName;

    public FileSudokuBoardDao(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public SudokuBoard read() {
        SudokuBoard obj = null;
        try (FileInputStream fOut = new FileInputStream(fileName)) {
            ObjectInputStream ois = new ObjectInputStream(fOut);
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
            ObjectOutputStream oos = new ObjectOutputStream(fOut);
            oos.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
