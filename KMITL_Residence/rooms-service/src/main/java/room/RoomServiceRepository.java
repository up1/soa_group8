package room;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import room.mapper.RoomRowMapper;
import room.mapper.RoomTypeRowMapper;
import room.model.Room;
import room.model.RoomType;

@Repository
public class RoomServiceRepository {
	
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Transactional(readOnly = true)
    public RoomType getTotalOfSpecificRoomType(int room_type_id){
        return this.jdbcTemplate.queryForObject("select Room_Type.*, count(Rooms.room_id) as total from Room_Type inner join rooms on room_type.type_id = rooms.room_type_id where room_type.type_id = ? group by rooms.room_type_id;", new Object[]{room_type_id}, new RoomTypeRowMapper());
    }

    @Transactional(readOnly = true)
    public List<RoomType> getListOfRoomType(int adults, int children){
        return this.jdbcTemplate.query("select Room_Type.*, count(Rooms.room_id) as total from Room_Type inner join rooms on room_type.type_id = rooms.room_type_id where room_type.type_adult_limit >= ? and room_type.type_children_limit >= ? group by rooms.room_type_id;", new Object[]{adults, children}, new RoomTypeRowMapper());
    }

    @Transactional(readOnly = true)
    public Room getRoomInformationByRoomId(int roomId){
        return this.jdbcTemplate.queryForObject("SELECT * FROM Rooms WHERE room_id = ?", new Object[]{roomId}, new RoomRowMapper());
    }

    @Transactional(readOnly = true)
    public List<Room> getAvailableRoomsByRoomTypeId(int roomTypeId){
        return this.jdbcTemplate.query("SELECT * FROM Rooms WHERE room_type_id = ? and room_availability = 1", new Object[]{roomTypeId}, new RoomRowMapper());
    }

    @Transactional
    public void roomCheckInByReservationId(int roomId, int reservationId){
        String sql = "UPDATE Rooms SET room_availability = 0 WHERE room_id = ?";
        this.jdbcTemplate.update(sql, roomId);
        sql = "INSERT INTO RoomsChecker(reservation_id, room_id) VALUES (?,?)";
        this.jdbcTemplate.update(sql, roomId, reservationId);
    }

    @Transactional
    public void roomCheckOutByReservationId(int roomId, int reservationId){
        String sql = "UPDATE Rooms SET room_availability = 1 WHERE room_id = ?";
        this.jdbcTemplate.update(sql, roomId);
        sql = "UPDATE RoomsChecker SET checkout = CURRENT_TIMESTAMP WHERE reservation_id = ?";
        this.jdbcTemplate.update(sql, reservationId);
    }
}
