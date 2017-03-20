package reservation.model;

/**
 * Created by Adisorn on 11/3/2560.
 */
public class AvailableRoomsType {

    private int roomType;
    private int total;

    public AvailableRoomsType(int roomType, int total) {
        this.roomType = roomType;
        this.total = total;
    }

    public AvailableRoomsType() {

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

    public String toString() {
        return "{ 'roomType': " + roomType + ", 'total': " + total;
    }
}
