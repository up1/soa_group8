package reservation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Adisorn on 17/3/2560.
 */
@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class CancelDeniedException extends RuntimeException {
    public CancelDeniedException(int reservationId) {
        super("This reservation id contain status waiting or cancel: " + reservationId);
    }
}