package user.model;

/**
 * Created by Adisorn on 2/4/2560.
 */
public class ResultMessage {

    private String username;
    private String message;

    public ResultMessage(String username, String message) {
        this.username = username;
        this.message = message;
    }

    public ResultMessage() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
