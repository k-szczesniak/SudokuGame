package sudokupackage.exceptions;

import java.util.ResourceBundle;

public class StageException extends Exception {

    ResourceBundle bundle = ResourceBundle.getBundle("Language");

    public StageException() {
        super();
    }

    public StageException(Throwable cause) {
        super(cause);
    }

    public StageException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getLocalizedMessage() {
        return bundle.getString(getMessage());
    }
}
