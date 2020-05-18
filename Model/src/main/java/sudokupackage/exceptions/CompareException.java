package sudokupackage.exceptions;

import java.util.ResourceBundle;

public class CompareException extends RuntimeException {

    ResourceBundle bundle = ResourceBundle.getBundle("Language");

    public CompareException(String message) {
        super(message);
    }

    @Override
    public String getLocalizedMessage() {
        return bundle.getString(getMessage());
    }
}
