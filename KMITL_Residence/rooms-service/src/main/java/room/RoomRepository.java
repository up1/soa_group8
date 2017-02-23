package room;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import room.RoomRowMapper;

@Repository
public class RoomRepository {
	
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Transactional(readOnly = true)
    public List<Room> getAvailableRooms(){
    	List<Room> rooms = this.jdbcTemplate.query("SELECT * FROM ROOMS WHERE room_status=1", new RoomRowMapper());
    	return rooms;
    }
}
