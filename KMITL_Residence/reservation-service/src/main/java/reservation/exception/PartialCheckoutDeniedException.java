package reservation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Adisorn on 18/3/2560.
 */
@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class PartialCheckoutDeniedException extends RuntimeException {

    public PartialCheckoutDeniedException(int reservationId) {
        super("This reservation id contain status waiting or cancel: " + reservationId);
    }

}
