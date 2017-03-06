package room;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import room.model.Room;

public class RoomRowMapper implements RowMapper<Room>{

	@Override
	public Room mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
		// TODO Auto-generated method stub
		Room room = new Room();
		room.setRoom_id(resultSet.getInt("room_id"));
		room.setRoom_detail(resultSet.getString("room_detail"));
		room.setRoom_status(resultSet.getInt("room_status"));
		room.setRoom_type_id(resultSet.getInt("room_type_id"));
		return room;
	}
	
}
