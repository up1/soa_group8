package room;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import room.model.Room;
import room.model.RoomType;

@Repository
public class RoomRepository {
	
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Transactional(readOnly = true)
    public RoomType getTotalOfSpecificRoomType(int room_type_id){
        return this.jdbcTemplate.queryForObject("select Room_Type.*, count(Rooms.room_id) as total from Room_Type inner join rooms on room_type.type_id = rooms.room_type_id where room_type.type_id = ? group by rooms.room_type_id;", new Object[]{room_type_id}, new RoomTypeRowMapper());
    }
}
