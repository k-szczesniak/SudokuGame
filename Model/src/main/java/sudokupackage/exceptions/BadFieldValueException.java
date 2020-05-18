package sudokupackage.exceptions;

import java.util.ResourceBundle;

public class BadFieldValueException extends IllegalArgumentException {

    ResourceBundle bundle = ResourceBundle.getBundle("Language");

    public BadFieldValueException(String message) {
        super(message);
    }

    @Override
    public String getLocalizedMessage() {
        return bundle.getString(getMessage());
    }
}
