package room.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by madooding on 3/9/2017 AD.
 */

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ReceptionException extends RuntimeException {
    public ReceptionException (String msg)
    {
        super(msg);
    }
}
