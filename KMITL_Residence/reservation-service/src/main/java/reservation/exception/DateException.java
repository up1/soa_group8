package reservation.exception;

/**
 * Created by Adisorn on 21/3/2560.
 */
public class DateException extends RuntimeException {
    public DateException(String checkIn, String checkout) {
        super("Date not allow: checkIn = "  + checkIn + ", checkout = " + checkout);
    }
}
