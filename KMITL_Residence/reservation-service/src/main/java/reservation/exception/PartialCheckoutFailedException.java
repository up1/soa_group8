package reservation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Adisorn on 18/3/2560.
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class PartialCheckoutFailedException extends RuntimeException {
    public PartialCheckoutFailedException(int reservationId) {
        super("Update failed id: " + reservationId);
    }
}
