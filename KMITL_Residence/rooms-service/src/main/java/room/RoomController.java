package room;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import room.model.Room;
import room.model.RoomType;

@RestController
@CrossOrigin(origins = "http://localhost:9000")
public class RoomController {
	private final RoomRepository roomRepository;

	@Autowired
	public RoomController(RoomRepository roomRepository){
		this.roomRepository = roomRepository;
	}

//	@RequestMapping("/rooms")
//	public List<Room> getAvailableRooms(){
//		return this.roomRepository.getAvailableRooms();
//	}

	@RequestMapping(value = "/rooms/type/{room_type_id}", method = RequestMethod.GET)
	public RoomType getRoomTypeInformationSpecificById(@PathVariable int room_type_id){
		return this.roomRepository.getTotalOfSpecificRoomType(room_type_id);
	}
	
}
