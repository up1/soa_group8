package reservation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Adisorn on 17/3/2560.
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CancelFailedException extends RuntimeException {
    public CancelFailedException(int reservationId) {
        super("Cancel failed id: " + reservationId);
    }
}
