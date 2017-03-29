package reservation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Adisorn on 17/3/2560.
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class ConfirmDeniedException extends RuntimeException {

    public ConfirmDeniedException(int reservationId) {
        super("This reservation id is already confirmed or cancelled: " + reservationId);
    }

}
