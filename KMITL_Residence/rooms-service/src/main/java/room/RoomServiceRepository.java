package room;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.client.RestTemplate;
import room.exception.*;
import room.mapper.CheckerRowMapper;
import room.mapper.RoomRowMapper;
import room.mapper.RoomTypeRowMapper;
import room.model.Checker;
import room.model.Reservation;
import room.model.Room;
import room.model.RoomType;

@Repository
public class RoomServiceRepository {
	
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Transactional(readOnly = true)
    public RoomType getTotalOfSpecificRoomType(int room_type_id){
        RoomType roomType;
        try {
            roomType = this.jdbcTemplate.queryForObject(
                    "select Room_Type.*, count(Rooms.room_id) as total from Room_Type " +
                            "inner join rooms on room_type.type_id = rooms.room_type_id " +
                            "where room_type.type_id = ? group by rooms.room_type_id;",
                    new Object[]{room_type_id},
                    new RoomTypeRowMapper());
        } catch (Exception e){
            throw new RoomTypeNotFound(room_type_id);
        }
        return roomType;
    }

    @Transactional(readOnly = true)
    public List<RoomType> getListOfRoomType(int adults, int children){
        List<RoomType> roomTypeList = this.jdbcTemplate.query(
                "select Room_Type.*, count(Rooms.room_id) as total from Room_Type " +
                        "inner join rooms on room_type.type_id = rooms.room_type_id " +
                        "where room_type.type_adult_limit >= ? and room_type.type_children_limit >= ? " +
                        "group by rooms.room_type_id;",
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
                    "SELECT room_id, room_details, room_type_id, room_availability FROM Rooms WHERE room_id = ?",
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
                "SELECT room_id, room_details, room_type_id, room_availability FROM Rooms " +
                        "WHERE room_type_id = ? and room_availability = 1",
                new Object[]{roomTypeId},
                new RoomRowMapper());
        if(rooms.size() == 0){
            throw new RoomNotFound("There are no available rooms type : " + roomTypeId);
        }
        return rooms;
    }

    @Transactional
    public void roomCheckInByReservationId(int roomId, int reservationId){

        RestTemplate template = new RestTemplate();
        Reservation reservation = new Reservation();
        try {
            reservation = template.getForObject("http://localhost:9000/reservation/" + reservationId + "/", Reservation.class);
        }catch(Exception e) {
            throw new ReservationNotFoundException(reservationId);
        }

        Room room = getRoomInformationByRoomId(roomId);

        if(reservation.getRoomType() == room.getRoomTypeId() && reservation.getId() == reservationId) {

            if (room.getRoomAvailability() == 0) {
                throw new RoomIsUnavailable(roomId);
            }
            List<Checker> checkers = this.jdbcTemplate.query(
                    "SELECT reservation_id, checkin, checkout, room_id FROM ROOMSCHECKER WHERE reservation_id = ?",
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
    public void roomCheckOutByReservationId(int roomId, int reservationId){
        List<Checker> checkers = this.jdbcTemplate.query(
                "SELECT reservation_id, checkin, checkout, room_id FROM ROOMSCHECKER WHERE reservation_id = ?",
                new Object[]{reservationId},
                new CheckerRowMapper());
        if(checkers.size() == 0){
            throw new ReceptionException("This reservation id has not checked-in or invalid, Reservation id : " + reservationId);
        }else if(checkers.get(0).getCheckout() != null){
            throw new ReceptionException("This reservation id has already checked-out, Reservation id : " + reservationId);
        }
        String sql = "UPDATE Rooms SET room_availability = 1 WHERE room_id = ?";
        this.jdbcTemplate.update(sql, roomId);
        sql = "UPDATE RoomsChecker SET checkout = CURRENT_TIMESTAMP WHERE reservation_id = ?";
        this.jdbcTemplate.update(sql, reservationId);
    }
}
