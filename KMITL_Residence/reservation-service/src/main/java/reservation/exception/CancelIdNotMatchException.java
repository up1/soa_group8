package reservation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Adisorn on 30/3/2560.
 */
@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class CancelIdNotMatchException extends RuntimeException {
    public CancelIdNotMatchException(int id) {
        super("Cancel ID not match: " + id);
    }
}
