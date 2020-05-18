package sudokupackage.exceptions;

import java.util.ResourceBundle;

public class DaoException extends Exception {

    ResourceBundle bundle = ResourceBundle.getBundle("Language");

    public DaoException() {
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getLocalizedMessage() {
        return bundle.getString(getMessage());
    }
}
