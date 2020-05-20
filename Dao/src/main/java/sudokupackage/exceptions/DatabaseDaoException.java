package sudokupackage.exceptions;

public class DatabaseDaoException extends DaoException {

    public DatabaseDaoException(Throwable cause) {
        super(cause);
    }

    public DatabaseDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
