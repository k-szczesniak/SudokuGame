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
    public SudokuBoard read() throws Exception {
        try (FileInputStream fOut = new FileInputStream(fileName)) {
            ObjectInputStream ois = new ObjectInputStream(fOut);
            return (SudokuBoard) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
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