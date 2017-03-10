package room.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import room.model.Room;

public class RoomRowMapper implements RowMapper<Room>{

	@Override
	public Room mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
		// TODO Auto-generated method stub
		Room room = new Room();
		room.setRoomId(resultSet.getInt("room_id"));
		room.setRoomDetails(resultSet.getString("room_details"));
		room.setRoomAvailability(resultSet.getInt("room_availability"));
		room.setRoomTypeId(resultSet.getInt("room_type_id"));
		return room;
	}
	
}
