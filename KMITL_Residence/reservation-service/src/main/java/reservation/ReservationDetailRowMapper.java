package reservation;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Adisorn on 6/3/2560.
 */
public class ReservationDetailRowMapper implements RowMapper<ReservationDetail> {
    @Override
    public ReservationDetail mapRow(ResultSet rs, int i) throws SQLException {
        ReservationDetail reservation = new ReservationDetail();

        reservation.setReservation_id(rs.getInt("reservation_id"));
        reservation.setReservation_date(rs.getString("reservation_date"));
        reservation.setReservation_night(rs.getInt("reservation_night"));
        reservation.setReservation_total(rs.getInt("reservation_adult") + rs.getInt("reservation_children"));
        reservation.setReservation_status(rs.getString("status_description"));
        reservation.setReservation_payment_type(rs.getString("payment_type_description"));
        reservation.setRoom_type(rs.getInt("room_type"));
        reservation.setCustomer_title_name(rs.getString("customer_title_name"));
        reservation.setCustomer_full_name(rs.getString("customer_full_name"));
        reservation.setCustomer_email(rs.getString("customer_email"));
        reservation.setCustomer_tel(rs.getString("customer_tel"));
        reservation.setCustomer_country(rs.getString("customer_country"));
        reservation.setCustomer_nation(rs.getString("customer_nation"));
        return reservation;
    }
}
