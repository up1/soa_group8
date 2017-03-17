package reservation.model;

/**
 * Created by Adisorn on 14/3/2560.
 */
public class ResultMessage {
    private String message;

    public ResultMessage(String message) {
        this.message = message;
    }

    public ResultMessage() {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
