package room;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import room.exception.*;
import room.mapper.CheckerRowMapper;
import room.mapper.RoomRowMapper;
import room.mapper.RoomTypeRowMapper;
import room.model.*;
import room.service.ReservationService;

@Repository
public class RoomServiceRepository {
	
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ReservationService reservationService;

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

        Reservation reservation = reservationService.getReservation(reservationId, token);

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
                throw new ReservationNotConfirmOrCancelException(reservationId);
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
    public void roomCheckOutByReservationId(int reservationId, String token){

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

        if(isCheckoutBefore(reservationId, token)) {
            reservationService.partialCheckout(reservationId, token);
        }
    }

    public ReservationInfo getInfoReservationCheck(int reservationId, String token) {

        ReservationInfo reservation;
        Checker checker = getCheckerFromReservationId(reservationId);
        if(checker == null) {
            reservation = getReservationWithCheckStatus(reservationService.getReservation(reservationId, token),
                    "no", "no", 0);
        }
        else {
            if(checker.getCheckout() == null) {
                reservation = getReservationWithCheckStatus(reservationService.getReservation(reservationId, token),
                        "yes", "no", checker.getRoomId());
            }
            else {
                reservation = getReservationWithCheckStatus(reservationService.getReservation(reservationId, token),
                        "yes", "yes", checker.getRoomId());
            }
        }
        return reservation;
    }

    private ReservationInfo getReservationWithCheckStatus(Reservation reservation, String checkIn, String checkOut, int roomId) {
        ReservationInfo reservationInfo = new ReservationInfo(reservation.getId(),
                reservation.getCheckIn(),
                reservation.getCheckOut(),
                reservation.getTotal(),
                reservation.getRoomType(),
                reservation.getStatus(),
                reservation.getCustomer(),
                checkIn,
                checkOut,
                roomId);
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
        Room room = getRoomInformationByRoomId(roomId);
        if(room.getRoomAvailability() == 0) {
            throw new ClosedRoomDeniedException(roomId);
        }

        this.jdbcTemplate.update(sql, new Object[] {roomId});
    }

    public void openRoom(int roomId) {
        String sql = "update Rooms set room_availability = 1 where room_id = ?";
        this.jdbcTemplate.update(sql, new Object[] {roomId});
    }

    public void changeRoom(int reservationId, int roomId, String token) {
        ReservationInfo reservation = getInfoReservationCheck(reservationId, token);
        Room room = getRoomInformationByRoomId(roomId);
        Checker checker = getCheckerFromReservationId(reservationId);
        if(reservation.getStatus().equals("waiting") || reservation.getStatus().equals("cancel")) {
            throw new ReservationNotConfirmOrCancelException(reservationId);
        }
        else if(reservation.getCheckInStatus().equals("no")) {
            throw new ReservationNotCheckInException(reservationId);
        }
        else if(room.getRoomTypeId() != reservation.getRoomType()) {
            throw new ReservationNotMatchException(reservationId);
        }
        else if(room.getRoomAvailability() == -1 || room.getRoomAvailability() == 0) {
            throw new RoomIsUnavailable(roomId);
        }

        String sql = "update RoomsChecker set room_id = ? where reservation_id = ?";
        this.jdbcTemplate.update(sql, new Object[] { roomId,reservationId });
        String updateOldRoom = "update Rooms set room_availability = 1 where room_id = ?";
        String updateNewRoom = "update Rooms set room_availability = 0 where room_id = ?";
        this.jdbcTemplate.update(updateOldRoom, new Object[] { checker.getRoomId() });
        this.jdbcTemplate.update(updateNewRoom, new Object[] { roomId });

    }

    private Checker getCheckerFromReservationId(int reservationId) {
        Checker checker;
        String sql = "SELECT reservation_id, checkin, checkout, room_id FROM RoomsChecker WHERE reservation_id = ?;";
        try {
            checker = this.jdbcTemplate.queryForObject(sql, new Object[]{reservationId}, new CheckerRowMapper());
        } catch (Exception ex) {
            checker = null;
        }
        return checker;
    }

    private boolean isCheckoutBefore(int reservationId, String token) {
        Reservation reservation = reservationService.getReservation(reservationId, token);
        Checker checker = getCheckerFromReservationId(reservationId);

        String oldCheckout = reservation.getCheckOut();
        String newCheckout = checker.getCheckout().split(" ")[0];
        int result = oldCheckout.compareTo(newCheckout);
        boolean isBefore = true;
        if(result > 0) isBefore = true;
        else if(result < 0) isBefore = false;
        else isBefore = true;
        return isBefore;
    }

}
