package room.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by madooding on 3/9/2017 AD.
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoomNotFound extends RuntimeException{

    public RoomNotFound(int roomId){
        super("Room not found id : " + roomId);
    }


    public RoomNotFound(String msg){
        super(msg);
    }
}
