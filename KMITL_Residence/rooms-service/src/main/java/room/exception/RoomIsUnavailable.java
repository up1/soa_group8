package room.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import room.model.Room;

/**
 * Created by madooding on 3/9/2017 AD.
 */

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RoomIsUnavailable extends RuntimeException {
    public RoomIsUnavailable(int roomId){
        super("Room id : " + roomId + " is unavailable");
    }
}
