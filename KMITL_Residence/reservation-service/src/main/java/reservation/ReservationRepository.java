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

}
