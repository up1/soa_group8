package room;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:9000")
public class RoomController {
	private final RoomRepository roomRepository;
	
	@Autowired
	public RoomController(RoomRepository roomRepository){
		this.roomRepository = roomRepository;
	}
	
	@RequestMapping("/rooms")
	public List<Room> getAvailableRooms(){
		return this.roomRepository.getAvailableRooms();
	}
	
	
	
}
