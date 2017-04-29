package reservation.repository;

import java.util.List;

import reservation.model.AvailableRoomsType;
import reservation.model.Email;
import reservation.model.Reservation;
import reservation.model.ReservationDetail;

public interface ReservationDAO {

    // 1. Get ReservationDetail object by reservation_id
    ReservationDetail getReservation(int reservation_id);

    // 2. Get full Reservation object by reservation_id
    Reservation getFullReservation(int reservation_id);

    // 3. Save reservation by Reservation object
    int saveReservation(Reservation reservation);

    // 4. Cancel reservation by reservation_id and cancel_id
    void cancelReservation(int reservation_id, String cancel_id);

    // 5. Confirm reservation by reservation_id and confirm_id
    void confirmReservation(int reservation_id, String confirm_id);

    // 6. Update partial check-out by reservation_id
    void updatePartialCheckout(int reservation_id);

    // 7. Cancel reservations
    void cancelReservations();

    // 8. Get List of available room by checkin, checkout, adults, and children
    List<AvailableRoomsType> searchAvailable(String checkin, String checkout, int adults, int children);

    // 9. Generate ID by Reservation object
    String getGenerateId(Reservation reservation);

    // 10. Get last insertion ID
    int getLastInsertId();

    // 11. Get day between two arguments
    int daysBetween(long t1, long t2);

    // 12. Send E-mail
    void sendEmail(Email email);

    // 13. Get available status of reservation
    boolean isAvailable(Reservation reservation, List<AvailableRoomsType> availableRoomsTypes);
}