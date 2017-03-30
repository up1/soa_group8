package reservation.model;

/**
 * Created by LAB207_43 on 14/3/2560.
 */
public class Content {

    private int reservationId;
    private int roomType;
    private String checkinTime;
    private String checkoutTime;
    private int totalNight;
    private int totalPrice;
    private String confirmationLink;
    private String cancelLink;

    public void Content() {

    }

    public void Content(int reservationId, int roomType, String checkinTime, String checkoutTime,
                        int totalNight, int totalPrice, String confirmationLink, String cancelLink) {
        this.reservationId = reservationId;
        this.roomType = roomType;
        this.checkinTime = checkinTime;
        this.checkoutTime = checkoutTime;
        this.totalNight = totalNight;
        this.totalPrice = totalPrice;
        this.confirmationLink = confirmationLink;
        this.cancelLink = cancelLink;
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

    public String getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(String checkinTime) {
        this.checkinTime = checkinTime;
    }

    public String getCheckoutTime() {
        return checkoutTime;
    }

    public void setCheckoutTime(String checkoutTime) {
        this.checkoutTime = checkoutTime;
    }

    public int getTotalNight() {
        return totalNight;
    }

    public void setTotalNight(int totalNight) {
        this.totalNight = totalNight;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getConfirmationLink() {
        return confirmationLink;
    }

    public void setConfirmationLink(String confirmationLink) {
        this.confirmationLink = confirmationLink;
    }

    public String getCancelLink() {
        return cancelLink;
    }

    public void setCancelLink(String cancelLink) {
        this.cancelLink = cancelLink;
    }
}
