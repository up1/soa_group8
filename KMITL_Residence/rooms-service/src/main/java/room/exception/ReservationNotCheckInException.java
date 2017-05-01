package room.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Adisorn on 1/5/2560.
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class ReservationNotCheckInException extends RuntimeException {

    public ReservationNotCheckInException(int reservationId) {
        super("Reservation ID not check-in: " + reservationId);
    }

}
