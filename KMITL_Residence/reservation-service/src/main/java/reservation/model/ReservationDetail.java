package reservation.model;

/**
 * Created by Adisorn on 6/3/2560.
 */
public class ReservationDetail {

    private int id;
    private String checkIn;
    private String checkOut;
    private int total;
    private int roomType;
    private String status;
    private String paymentType;
    private Customer customer;

    public ReservationDetail(int id, String checkIn, String checkOut, int total, int roomType,
                             String status, String paymentType, Customer customer) {

        this.id = id;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.total = total;
        this.roomType = roomType;
        this.status = status;
        this.paymentType = paymentType;
        this.customer = customer;

    }

    public ReservationDetail() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getRoomType() {
        return roomType;
    }

    public void setRoomType(int roomType) {
        this.roomType = roomType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
