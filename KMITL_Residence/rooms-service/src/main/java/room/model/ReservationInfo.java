package room.model;

/**
 * Created by Adisorn on 26/4/2560.
 */
public class ReservationInfo extends Reservation {

    private String checkInStatus;

    public ReservationInfo(int id, String checkIn, String checkOut,
                           int total, int roomType, String status,
                           Customer customer, String checkInStatus) {
        super(id, checkIn, checkOut, total, roomType, status, customer);
        this.checkInStatus = checkInStatus;
    }

    public ReservationInfo() {

    }

    public String getCheckInStatus() {
        return checkInStatus;
    }

    public void setCheckInStatus(String checkInStatus) {
        this.checkInStatus = checkInStatus;
    }
}
