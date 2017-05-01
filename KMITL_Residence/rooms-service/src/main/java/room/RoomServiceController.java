package room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import room.exception.UnauthorizedException;
import room.jwt.JwtService;
import room.model.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class RoomServiceController {
	private final RoomServiceRepository roomServiceRepository;

	@Autowired
    private JwtService service;

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
    public ResponseEntity roomCheckInByReservationId(@PathVariable int room_id,
                                                     @PathVariable int reservation_id,
                                                     @RequestHeader(value = "authenticate-token") String token){
        if(token == null) {
            throw new UnauthorizedException();
        }
        try {
            service.getUser(token);
        } catch (Exception ex) {
            throw new UnauthorizedException();
        }
        this.roomServiceRepository.roomCheckInByReservationId(room_id, reservation_id, token);
        return new ResponseEntity(new ResultMessage("Success"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/rooms/checkout", method = RequestMethod.PUT)
    public ResponseEntity roomCheckOutByReservationId(@RequestParam(value = "id") int reservationId,
                                                      @RequestHeader(value = "authenticate-token") String token){
        if(token == null) {
            throw new UnauthorizedException();
        }
        try {
            service.getUser(token);
        } catch (Exception ex) {
            throw new UnauthorizedException();
        }
        this.roomServiceRepository.roomCheckOutByReservationId(reservationId, token);
        return new ResponseEntity(new ResultMessage("Success"),HttpStatus.OK);
    }

    @RequestMapping(value = "/checkinfo", method = RequestMethod.GET)
    public ResponseEntity infoCheckin(@RequestParam(value = "id", required = true) int reservationId,
                                      @RequestHeader(value = "authenticate-token") String token) {
        if(token == null) {
            throw new UnauthorizedException();
        }
        try {
            service.getUser(token);
        } catch (Exception ex) {
            throw new UnauthorizedException();
        }
        ReservationInfo reservation = this.roomServiceRepository.getInfoReservationCheck(reservationId, token);
        return new ResponseEntity(reservation, HttpStatus.OK);
    }

    @RequestMapping(value = "/rooms", method = RequestMethod.GET)
    public List<Room> getRooms() {
        return this.roomServiceRepository.getRooms();
    }

    @RequestMapping(value = "/rooms/{roomId}/open", method = RequestMethod.PUT)
    public ResponseEntity openRoom(@PathVariable int roomId,
                                   @RequestHeader(value = "authenticate-token") String token) {
        if(token == null) {
            throw new UnauthorizedException();
        }
        try {
            service.getUser(token);
        } catch (Exception ex) {
            throw new UnauthorizedException();
        }
        this.roomServiceRepository.openRoom(roomId);
        return new ResponseEntity(new ResultMessage("OPEN ROOM: " + roomId ), HttpStatus.OK);
    }

    @RequestMapping(value = "/rooms/{roomId}/close", method = RequestMethod.PUT)
    public ResponseEntity closeRoom(@PathVariable int roomId,
                                   @RequestHeader(value = "authenticate-token") String token) {
        if(token == null) {
            throw new UnauthorizedException();
        }
        try {
            service.getUser(token);
        } catch (Exception ex) {
            throw new UnauthorizedException();
        }
        this.roomServiceRepository.closeRoom(roomId);
        return new ResponseEntity(new ResultMessage("CLOSE ROOM: " + roomId ), HttpStatus.OK);
    }

    @RequestMapping(value = "/rooms/change")
    public ResponseEntity changeRoom(@RequestParam(value = "reservationId") int reservationId,
                                     @RequestParam(value = "roomId") int roomId,
                                     @RequestHeader(value = "authenticate-token") String token) {

        if(token == null) {
            throw new UnauthorizedException();
        }
        try {
            service.getUser(token);
        } catch (Exception ex) {
            throw new UnauthorizedException();
        }

        this.roomServiceRepository.changeRoom(reservationId, roomId, token);

        return new ResponseEntity("Changed room success", HttpStatus.OK);
    }
}
