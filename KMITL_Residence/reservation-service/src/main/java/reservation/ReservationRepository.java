package reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adisorn on 1/3/2560.
 */
@Repository
public class ReservationRepository {

    @Autowired
    private JdbcTemplate jdbc;

    public ReservationRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Transactional(readOnly = true)
    public List<Reservation> getReservation() {
        List<Reservation> reservations = null;

        try {

            String sql = "select * from reservation";
            reservations = jdbc.query(sql, new ReservationRowMapper());

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new NotFoundException();
        }
        return reservations;
    }

    @Transactional
    public void saveReservation(Reservation reservation) {
        String sql = "insert into reservation values(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbc.update(sql, reservation.getReservation_date(),
                reservation.getReservation_night(),
                reservation.getReservation_adult(),
                reservation.getReservation_children(),
                reservation.getReservation_status(),
                reservation.getReservation_payment_type(),
                reservation.getRoom_type(),
                reservation.getCustomer_title_name(),
                reservation.getCustomer_full_name(),
                reservation.getCustomer_email(),
                reservation.getCustomer_tel(),
                reservation.getCustomer_country(),
                reservation.getCustomer_nation(),
                reservation.getCredit_card_id(),
                reservation.getCredit_card_expired_date(),
                reservation.getCredit_card_cvv());
    }

}
