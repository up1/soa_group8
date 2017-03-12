package reservation.mapper;

import org.springframework.jdbc.core.RowMapper;
import reservation.model.AvailableRoomsType;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Adisorn on 11/3/2560.
 */
public class AvailableRoomsTypeRowMapper implements RowMapper<AvailableRoomsType> {


    @Override
    public AvailableRoomsType mapRow(ResultSet rs, int i) throws SQLException {
        AvailableRoomsType availableRoomsType = new AvailableRoomsType();

        availableRoomsType.setRoomType(rs.getInt("room_type"));
        availableRoomsType.setTotal(rs.getInt("total"));

        return availableRoomsType;
    }
}
