package room.model;

/**
 * Created by Adisorn on 1/5/2560.
 */
public class ReservationMessage {

    private int id;
    private String message;

    public ReservationMessage(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public ReservationMessage() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
