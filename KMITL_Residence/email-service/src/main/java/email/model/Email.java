package email.model;

/**
 * Created by LAB207_43 on 14/3/2560.
 */
public class Email {

    private int reservation_id;
    private int email_type;
    private String destination;

    public void Email() {

    }

    public void Email(int reservation_id, int email_type, String destination) {
        this.reservation_id =  reservation_id;
        this.email_type = email_type;
        this.destination = destination;
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public int getEmail_type() {
        return email_type;
    }

    public void setEmail_type(int email_type) {
        this.email_type = email_type;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
