package room.model;

/**
 * Created by Adisorn on 26/4/2560.
 */
public class ReservationInfo extends Reservation {

    private String checkInStatus;

    public ReservationInfo(int id, String checkIn, String checkOut, int total, int roomType, String status, String paymentType, Customer customer, String checkInStatus) {
        super(id, checkIn, checkOut, total, roomType, status, paymentType, customer);
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
