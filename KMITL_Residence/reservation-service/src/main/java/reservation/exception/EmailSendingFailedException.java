package reservation.exception;

public class EmailSendingFailedException extends RuntimeException {

    public EmailSendingFailedException(String email) {
        super("Failed to send new email: " + email);
    }
}
