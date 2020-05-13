package sudokupackage.exceptions;

public class OpenSaveException extends StageException {
    public OpenSaveException() {
        super();
    }

    public OpenSaveException(Throwable cause) {
        super(cause);
    }

    public OpenSaveException(String message, Throwable cause) {
        super(message, cause);
    }
}
