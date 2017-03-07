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
        roomType.setTypeId(resultSet.getInt("type_id"));
        roomType.setTypeNameTh(resultSet.getString("type_name_th"));
        roomType.setTypeNameEn(resultSet.getString("type_name_en"));
        roomType.setTypeDetailsTh(resultSet.getString("type_details_th"));
        roomType.setTypeDetailsEn(resultSet.getString("type_details_en"));
        roomType.setTypePrice(resultSet.getDouble("type_price"));
        roomType.setTypeAdultLimit(resultSet.getInt("type_adult_limit"));
        roomType.setTypeChildrenLimit(resultSet.getInt("type_children_limit"));
        roomType.setTypeTotalRooms(resultSet.getInt("total"));
        return roomType;
    }
}
