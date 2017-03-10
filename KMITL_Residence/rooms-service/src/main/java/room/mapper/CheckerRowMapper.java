package room.mapper;

import org.springframework.jdbc.core.RowMapper;
import room.model.Checker;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by madooding on 3/9/2017 AD.
 */
public class CheckerRowMapper implements RowMapper<Checker>{
    @Override
    public Checker mapRow(ResultSet resultSet, int i) throws SQLException {
        Checker checker = new Checker();
        checker.setReservationId(resultSet.getInt("reservation_id"));
        checker.setCheckin(resultSet.getString("checkin"));
        checker.setCheckout(resultSet.getString("checkout"));
        checker.setRoomId(resultSet.getInt("room_id"));
        return checker;
    }
}
