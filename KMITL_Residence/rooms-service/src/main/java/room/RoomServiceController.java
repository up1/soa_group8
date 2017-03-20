package room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import room.model.Room;
import room.model.RoomType;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class RoomServiceController {
	private final RoomServiceRepository roomServiceRepository;

	@Autowired
	public RoomServiceController(RoomServiceRepository roomServiceRepository){
		this.roomServiceRepository = roomServiceRepository;
	}

	@RequestMapping(value = "/rooms/type/{room_type_id}", method = RequestMethod.GET)
	public RoomType getRoomTypeInformationSpecificById(@PathVariable int room_type_id){
		return this.roomServiceRepository.getTotalOfSpecificRoomType(room_type_id);
	}

	@RequestMapping(value = "/rooms/types", method = RequestMethod.GET)
	public List<RoomType> getListOfRoomType(@RequestParam(value = "adults", required = true) int adults,
                                            @RequestParam(value = "children", required = true) int children){
		return this.roomServiceRepository.getListOfRoomType(adults, children);
	}

	@RequestMapping(value = "/rooms/{room_id}", method = RequestMethod.GET)
    public Room getRoomInformationByRoomId(@PathVariable int room_id){
	    return this.roomServiceRepository.getRoomInformationByRoomId(room_id);
    }

    @RequestMapping(value = "/rooms/available/{room_type_id}", method = RequestMethod.GET)
    public List<Room> getAvailableRoomsByRoomTypeId(@PathVariable int room_type_id){
        return this.roomServiceRepository.getAvailableRoomsByRoomTypeId(room_type_id);
    }

    @RequestMapping(value = "/rooms/{room_id:.*}/checkin/{reservation_id:.*}", method = RequestMethod.POST)
    public ResponseEntity roomCheckInByReservationId(@PathVariable int room_id, @PathVariable int reservation_id){
        this.roomServiceRepository.roomCheckInByReservationId(room_id, reservation_id);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/rooms/{room_id:.*}/checkout/{reservation_id:.*}", method = RequestMethod.PUT)
    public ResponseEntity roomCheckOutByReservationId(@PathVariable int room_id, @PathVariable int reservation_id){
        this.roomServiceRepository.roomCheckOutByReservationId(room_id, reservation_id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
