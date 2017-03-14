package email.model;

/**
 * Created by LAB207_43 on 14/3/2560.
 */
public class Content {

    private int room_type;
    private int price;
    private String confirmation_link;


    public void Content() {

    }

    public void Content(int room_type, int price, String confirmation_link) {
        this.setRoom_type(room_type);
        this.setPrice(price);
        this.setConfirmation_link(confirmation_link);
    }

    public int getRoom_type() {
        return room_type;
    }

    public void setRoom_type(int room_type) {
        this.room_type = room_type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getConfirmation_link() {
        return confirmation_link;
    }

    public void setConfirmation_link(String confirmation_link) {
        this.confirmation_link = confirmation_link;
    }
}
