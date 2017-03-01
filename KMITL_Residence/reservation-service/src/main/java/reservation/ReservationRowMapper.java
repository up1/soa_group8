package reservation;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Adisorn on 1/3/2560.
 */
public class ReservationRowMapper implements RowMapper<Reservation> {
    @Override
    public Reservation mapRow(ResultSet rs, int i) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setReservation_id(rs.getInt("reservation_id"));
        reservation.setReservation_date(rs.getString("reservation_date"));
        reservation.setReservation_night(rs.getInt("reservation_night"));
        reservation.setReservation_adult(rs.getInt("reservation_adult"));
        reservation.setReservation_children(rs.getInt("reservation_children"));
        reservation.setReservation_status(rs.getInt("reservation_status"));
        reservation.setReservation_payment_type(rs.getInt("reservation_payment_type"));
        reservation.setRoom_type(rs.getInt("room_type"));
        reservation.setRoom_id(rs.getInt("room_id"));

        reservation.setCustomer_title_name(rs.getString("customer_title_name"));
        reservation.setCustomer_full_name(rs.getString("customer_full_name"));
        reservation.setCustomer_email(rs.getString("customer_email"));
        reservation.setCustomer_tel(rs.getString("customer_tel"));
        reservation.setCustomer_country(rs.getString("customer_country"));
        reservation.setCustomer_nation(rs.getString("customer_nation"));

        reservation.setCredit_card_id(rs.getString("credit_card_id"));
        reservation.setCredit_card_expired_date(rs.getString("credit_card_expired_date"));
        reservation.setCredit_card_cvv(rs.getString("credit_card_cvv"));

        return reservation;
    }
}
