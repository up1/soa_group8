package room.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Adisorn on 16/3/2560.
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class ReservationNotMatchException extends RuntimeException {
    public ReservationNotMatchException(int reservationId) {
        super("Reservation id not match with room: " + reservationId);
    }
}
