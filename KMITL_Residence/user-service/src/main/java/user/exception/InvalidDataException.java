package user.exception;

/**
 * Created by Akkapon on 2/5/2560.
 */
public class InvalidDataException extends RuntimeException {
    public InvalidDataException() {
        super("Invalid Data");
    }

    public InvalidDataException(String action) {
        super("Invalid Data: " + action);
    }
}
