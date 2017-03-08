package room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import room.model.Room;
import room.model.RoomType;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:9000")
public class RoomServiceController {
	private final RoomServiceRepository roomServiceRepository;

	@Autowired
	public RoomServiceController(RoomServiceRepository roomServiceRepository){
		this.roomServiceRepository = roomServiceRepository;
	}

//	@RequestMapping("/rooms")
//	public List<Room> getAvailableRooms(){
//		return this.roomRepository.getAvailableRooms();
//	}

	@RequestMapping(value = "/rooms/type/{room_type_id}", method = RequestMethod.GET)
	public RoomType getRoomTypeInformationSpecificById(@PathVariable int room_type_id){
		return this.roomServiceRepository.getTotalOfSpecificRoomType(room_type_id);
	}

	@RequestMapping(value = "/rooms/types/{adults}/{children}", method = RequestMethod.GET)
	public List<RoomType> getListOfRoomType(@PathVariable int adults, @PathVariable int children){
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

}
