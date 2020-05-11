package sudokupackage.exceptions;

public class BadFieldValueException extends IllegalArgumentException {

    public BadFieldValueException(String message) {
        super(message);
    }
}
