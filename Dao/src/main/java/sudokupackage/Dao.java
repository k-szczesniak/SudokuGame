package sudokupackage;

import sudokupackage.exceptions.DaoException;

public interface Dao<T> extends AutoCloseable {
    public T read() throws DaoException;

    public void write(T obj) throws DaoException;
}
