package room;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.client.RestTemplate;
import room.exception.*;
import room.jwt.JwtService;
import room.mapper.CheckerRowMapper;
import room.mapper.RoomRowMapper;
import room.mapper.RoomTypeRowMapper;
import room.model.*;

@Repository
public class RoomServiceRepository {
	
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private JwtService jwtService;

    @Value("${url.reservation.service}")
    String reservationURL;

    @Value("${authentication.token.header}")
    String authTokenHeader;

    @Transactional(readOnly = true)
    public RoomType getTotalOfSpecificRoomType(int Room_Type_id){
        RoomType roomType;
        try {
            roomType = this.jdbcTemplate.queryForObject(
                    "select Room_Type.*, count(Rooms.room_id) as total from Room_Type " +
                            "inner join Rooms on Room_Type.type_id = Rooms.Room_Type_id " +
                            "where Room_Type.type_id = ? group by Rooms.Room_Type_id;",
                    new Object[]{Room_Type_id},
                    new RoomTypeRowMapper());
        } catch (Exception e){
            throw new RoomTypeNotFound(Room_Type_id);
        }
        return roomType;
    }

    @Transactional(readOnly = true)
    public List<RoomType> getListOfRoomType(int adults, int children){
        List<RoomType> roomTypeList = this.jdbcTemplate.query(
                "select Room_Type.*, count(Rooms.room_id) as total from Room_Type " +
                        "inner join Rooms on Room_Type.type_id = Rooms.Room_Type_id " +
                        "where Room_Type.type_adult_limit >= ? and Room_Type.type_children_limit >= ? " +
                        "group by Rooms.Room_Type_id;",
                new Object[]{adults, children},
                new RoomTypeRowMapper());
        if(roomTypeList.size() == 0){
            throw  new RoomTypeNotFound(adults, children);
        }
        return roomTypeList;
    }

    @Transactional(readOnly = true)
    public Room getRoomInformationByRoomId(int roomId){
        Room room;
        try {
            room = this.jdbcTemplate.queryForObject(
                    "SELECT room_id, room_details, Room_Type_id, room_availability FROM Rooms WHERE room_id = ?",
                    new Object[]{roomId},
                    new RoomRowMapper());
        }catch (Exception e){
            throw new RoomNotFound(roomId);
        }
        return room;
    }

    @Transactional(readOnly = true)
    public List<Room> getAvailableRoomsByRoomTypeId(int roomTypeId){
        List<Room> rooms = this.jdbcTemplate.query(
                "SELECT room_id, room_details, Room_Type_id, room_availability FROM Rooms " +
                        "WHERE Room_Type_id = ? and room_availability = 1",
                new Object[]{roomTypeId},
                new RoomRowMapper());
        if(rooms.size() == 0){
            throw new RoomNotFound("There are no available rooms type : " + roomTypeId);
        }
        return rooms;
    }

    @Transactional
    public void roomCheckInByReservationId(int roomId, int reservationId, String token){

        Reservation reservation = getReservation(reservationId, token);

        Room room = getRoomInformationByRoomId(roomId);

        if(reservation.getRoomType() == room.getRoomTypeId() && reservation.getId() == reservationId) {

            if (room.getRoomAvailability() == 0) {
                throw new RoomIsUnavailable(roomId);
            }
            List<Checker> checkers = this.jdbcTemplate.query(
                    "SELECT reservation_id, checkin, checkout, room_id FROM RoomsChecker WHERE reservation_id = ?",
                    new Object[]{reservationId},
                    new CheckerRowMapper());
            if (checkers.size() > 0) {
                throw new ReceptionException("This reservation id has already checked-in, Reservation id : " + reservationId);
            }
            if(reservation.getStatus().equals("waiting")) {
                throw new ReservationNotConfirmException(reservationId);
            }
            String sql = "UPDATE Rooms SET room_availability = 0 WHERE room_id = ?";
            this.jdbcTemplate.update(sql, roomId);
            sql = "INSERT INTO RoomsChecker(reservation_id, room_id) VALUES (?,?)";
            this.jdbcTemplate.update(sql, reservationId, roomId);
        }
        else {
            throw new ReservationNotMatchException(reservationId);
        }

    }

    @Transactional
    public void roomCheckOutByReservationId(int reservationId){

        List<Checker> checkers = this.jdbcTemplate.query(
                "SELECT reservation_id, checkin, checkout, room_id FROM RoomsChecker WHERE reservation_id = ?",
                new Object[]{reservationId},
                new CheckerRowMapper());
        if(checkers.size() == 0){
            throw new ReceptionException("This reservation id has not checked-in or invalid, Reservation id : " + reservationId);
        }else if(checkers.get(0).getCheckout() != null){
            throw new ReceptionException("This reservation id has already checked-out, Reservation id : " + reservationId);
        }
        String sql = "UPDATE Rooms SET room_availability = 1 WHERE room_id = ?";
        this.jdbcTemplate.update(sql, checkers.get(0).getRoomId());
        sql = "UPDATE RoomsChecker SET checkout = CURRENT_TIMESTAMP WHERE reservation_id = ?";
        this.jdbcTemplate.update(sql, reservationId);
    }

    public ReservationInfo getInfoReservationCheckin(int reservationId, String token) {
        String sql = "select reservation_id from RoomsChecker where reservation_id = ?;";
        ReservationInfo reservation;
        List<Map<String, Object>> id = this.jdbcTemplate.queryForList(sql, new String[] { Integer.toString(reservationId) });
        if(id.size() < 1) {
            reservation = getReservationWithCheckinStatus(getReservation(reservationId, token), "no");
        }
        else {
            reservation = getReservationWithCheckinStatus(getReservation(reservationId, token), "yes");
        }
        return reservation;
    }

    private Reservation getReservation(int reservationId, String token) {

        HttpHeaders headers = new HttpHeaders();
        headers.set(authTokenHeader, token);

        HttpEntity<String> entity = new HttpEntity<String>(headers);

        RestTemplate template = new RestTemplate();
        Reservation reservation;
        try {
            //reservation = template.getForObject(reservationURL + reservationId + "/", Reservation.class, entity);
            ResponseEntity<Reservation> reservationResponseEntity =
                    template.exchange(reservationURL + reservationId + "/", HttpMethod.GET, entity, Reservation.class);
            reservation = reservationResponseEntity.getBody();
        }catch(Exception e) {
            throw new ReservationNotFoundException(reservationId);
        }
        return reservation;
    }

    private ReservationInfo getReservationWithCheckinStatus(Reservation reservation, String status) {
        ReservationInfo reservationInfo = new ReservationInfo(reservation.getId(),
                reservation.getCheckIn(),
                reservation.getCheckOut(),
                reservation.getTotal(),
                reservation.getRoomType(),
                reservation.getStatus(),
                reservation.getCustomer(),
                status);
        return reservationInfo;
    }

    @Transactional(readOnly = true)
    public List<Room> getRooms() {
        List<Room> rooms;
        String sql = "select room_id, room_details, room_type_id, room_availability from Rooms";
        rooms = this.jdbcTemplate.query(sql, new RoomRowMapper());
        return rooms;
    }

    public void closeRoom(int roomId) {
        String sql = "update Rooms set room_availability = -1 where room_id = ?";
        this.jdbcTemplate.update(sql, new String[] {Integer.toString(roomId)});
    }

    public void openRoom(int roomId) {
        String sql = "update Rooms set room_availability = 1 where room_id = ?";
        this.jdbcTemplate.update(sql, new String[] {Integer.toString(roomId)});
    }

    public void changeRoom(int reservationId, int roomId, String token) {
        ReservationInfo reservation = getInfoReservationCheckin(reservationId, token);
        Room room = getRoomInformationByRoomId(roomId);
        if(reservation.getStatus().equals("no")) {
            throw new ReservationNotConfirmException(reservationId);
        }
        else if(room.getRoomTypeId() != reservation.getRoomType()) {
            throw new ReservationNotMatchException(reservationId);
        }
        String sql = "update RoomsChecker set room_id = ? where reservation_id = ?";
        this.jdbcTemplate.update(sql, new String[] { Integer.toString(roomId), Integer.toString(reservationId) });
    }

}
