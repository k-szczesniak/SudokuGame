package sudokupackage;

public interface Dao<T> extends AutoCloseable {
    public T read() throws Exception;

    public void write(T obj) throws Exception;
}
