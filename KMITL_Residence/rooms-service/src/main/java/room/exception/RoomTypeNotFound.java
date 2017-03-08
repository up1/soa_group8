package room.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by madooding on 3/8/2017 AD.
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoomTypeNotFound extends RuntimeException{
    public RoomTypeNotFound(int roomTypeId) {
        super("Could not find room type id : " + roomTypeId);
    }

    public RoomTypeNotFound(int adults, int children){
        super("There are no type of rooms that match with adults limit : " + adults + " and children limit : " + children);
    }
}
