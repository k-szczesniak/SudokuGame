package sudokupackage;

public interface Dao<T> {
    public T read() throws Exception;

    public void write(T obj) throws Exception;
}
