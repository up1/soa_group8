package reservation.model;

/**
 * Created by Adisorn on 14/3/2560.
 */
public class ResultMessage {
    private int id;
    private String message;

    public ResultMessage(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public ResultMessage() {

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
