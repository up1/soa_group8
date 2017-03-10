package reservation.mapper;

import org.springframework.jdbc.core.RowMapper;
import reservation.model.CreditCard;
import reservation.model.Customer;
import reservation.model.Reservation;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Adisorn on 1/3/2560.
 */
public class ReservationRowMapper implements RowMapper<Reservation> {
    @Override
    public Reservation mapRow(ResultSet rs, int i) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setId(rs.getInt("reservation_id"));
        reservation.setCheckIn(rs.getString("reservation_date"));
        reservation.setCheckOut(rs.getString("reservation_checkout"));
        reservation.setAdults(rs.getInt("reservation_adult"));
        reservation.setChildren(rs.getInt("reservation_children"));
        reservation.setStatus(rs.getInt("reservation_status"));
        reservation.setPaymentType(rs.getInt("reservation_payment_type"));
        reservation.setRoomType(rs.getInt("room_type"));

        Customer customer = new Customer();

        customer.setTitleName(rs.getString("customer_title_name"));
        customer.setFullName(rs.getString("customer_full_name"));
        customer.setEmail(rs.getString("customer_email"));
        customer.setTel(rs.getString("customer_tel"));
        customer.setCountry(rs.getString("customer_country"));
        customer.setNation(rs.getString("customer_nation"));

        CreditCard creditCard = new CreditCard();

        creditCard.setNumber(rs.getString("credit_card_id"));
        creditCard.setExpiredDate(rs.getString("credit_card_expired_date"));
        creditCard.setCvv(rs.getString("credit_card_cvv"));

        reservation.setCustomer(customer);
        reservation.setCreditCard(creditCard);

        return reservation;
    }
}
