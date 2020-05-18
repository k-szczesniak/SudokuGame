package sudokupackage.exceptions;

public class FileDaoException extends DaoException {

    public FileDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileDaoException(String message) {
        super(message);
    }
}
