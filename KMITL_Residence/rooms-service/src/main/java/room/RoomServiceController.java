package room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import room.model.RoomType;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:9000")
public class RoomServiceController {
	private final RoomTypeRepository roomTypeRepository;

	@Autowired
	public RoomServiceController(RoomTypeRepository roomTypeRepository){
		this.roomTypeRepository = roomTypeRepository;
	}

//	@RequestMapping("/rooms")
//	public List<Room> getAvailableRooms(){
//		return this.roomRepository.getAvailableRooms();
//	}

	@RequestMapping(value = "/rooms/type/{room_type_id}", method = RequestMethod.GET)
	public RoomType getRoomTypeInformationSpecificById(@PathVariable int room_type_id){
		return this.roomTypeRepository.getTotalOfSpecificRoomType(room_type_id);
	}

	@RequestMapping(value = "/rooms/types/{adults}/{children}", method = RequestMethod.GET)
	public List<RoomType> getListOfRoomType(@PathVariable int adults, @PathVariable int children){
		return this.roomTypeRepository.getListOfRoomType(adults, children);
	}

}
