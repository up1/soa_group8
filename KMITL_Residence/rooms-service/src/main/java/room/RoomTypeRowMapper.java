package room;

import org.springframework.jdbc.core.RowMapper;
import room.model.RoomType;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by madooding on 3/7/2017 AD.
 */
public class RoomTypeRowMapper implements RowMapper<RoomType> {


    @Override
    public RoomType mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        // TODO Auto-generated method stub
        RoomType roomType = new RoomType();
        roomType.setType_id(resultSet.getInt("type_id"));
        roomType.setType_name_th(resultSet.getString("type_name_th"));
        roomType.setType_name_en(resultSet.getString("type_name_en"));
        roomType.setType_details_th(resultSet.getString("type_details_th"));
        roomType.setType_details_en(resultSet.getString("type_details_en"));
        roomType.setType_price(resultSet.getDouble("type_price"));
        roomType.setType_adult_limit(resultSet.getInt("type_adult_limit"));
        roomType.setType_children_limit(resultSet.getInt("type_children_limit"));
        roomType.setType_total_rooms(resultSet.getInt("total"));
        return roomType;
    }
}
