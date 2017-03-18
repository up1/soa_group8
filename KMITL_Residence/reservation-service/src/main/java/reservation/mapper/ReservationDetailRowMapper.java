package reservation.mapper;

import org.springframework.jdbc.core.RowMapper;
import reservation.model.Customer;
import reservation.model.ReservationDetail;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Adisorn on 6/3/2560.
 */
public class ReservationDetailRowMapper implements RowMapper<ReservationDetail> {
    @Override
    public ReservationDetail mapRow(ResultSet rs, int i) throws SQLException {
        ReservationDetail reservation = new ReservationDetail();

        reservation.setId(rs.getInt("reservation_id"));
        reservation.setCheckIn(rs.getString("reservation_date"));
        reservation.setCheckOut(rs.getString("reservation_checkout"));
        reservation.setTotal(rs.getInt("reservation_adults") + rs.getInt("reservation_children"));
        reservation.setStatus(rs.getString("status_description"));
        reservation.setPartial(rs.getInt("reservation_partial"));
        reservation.setTimestamp(rs.getString("reservation_timestamp"));
        reservation.setRoomType(rs.getInt("room_type"));

        Customer customer = new Customer();

        customer.setTitleName(rs.getString("customer_title_name"));
        customer.setFullName(rs.getString("customer_full_name"));
        customer.setEmail(rs.getString("customer_email"));
        customer.setTel(rs.getString("customer_tel"));
        customer.setCountry(rs.getString("customer_country"));
        customer.setNation(rs.getString("customer_nation"));

        reservation.setCustomer(customer);

        return reservation;
    }
}
