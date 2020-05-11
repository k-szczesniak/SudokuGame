package sudokupackage;

import sudokupackage.exceptions.FileDaoException;

public interface Dao<T> extends AutoCloseable {
    public T read() throws FileDaoException;

    public void write(T obj) throws FileDaoException;
}
