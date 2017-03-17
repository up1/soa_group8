package email.model;

/**
 * Created by LAB207_43 on 14/3/2560.
 */
public class Content {

    private int reservationId;
    private int roomType;
    private int total;
    private String confirmationLink;

    public void Content() {

    }

    public void Content(int reservationId, int roomType, int total, String confirmationLink) {
        this.reservationId = reservationId;
        this.roomType = roomType;
        this.total = total;
        this.confirmationLink = confirmationLink;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getRoomType() {
        return roomType;
    }

    public void setRoomType(int roomType) {
        this.roomType = roomType;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getConfirmationLink() {
        return confirmationLink;
    }

    public void setConfirmationLink(String confirmationLink) {
        this.confirmationLink = confirmationLink;
    }
}
