package user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Adisorn on 3/4/2560.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidTokenException extends RuntimeException {

    public InvalidTokenException(String token) {
        super("Invalid token: " + token);
    }

}
