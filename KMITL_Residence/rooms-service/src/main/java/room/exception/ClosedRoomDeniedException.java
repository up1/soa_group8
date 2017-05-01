package room.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Adisorn on 1/5/2560.
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class ClosedRoomDeniedException extends RuntimeException {

    public ClosedRoomDeniedException(int roomId) {
        super("This room still have customer: " + roomId);
    }

}
