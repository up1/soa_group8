package room.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Adisorn on 16/3/2560.
 */
@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class ReservationNotMatchException extends RuntimeException {
    public ReservationNotMatchException(String msg) {
        super(msg);
    }
}
