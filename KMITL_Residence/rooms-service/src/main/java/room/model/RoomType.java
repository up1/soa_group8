package room.model;

/**
 * Created by madooding on 3/7/2017 AD.
 */
public class RoomType {
    private int type_id;
    private String type_name_th;
    private String type_name_en;
    private String type_details_th;
    private String type_details_en;
    private double type_price;
    private int type_adult_limit;
    private int type_children_limit;
    private int type_total_rooms;

    public RoomType(){

    }

    public RoomType(int type_id, String type_name_th, String type_name_en, String type_details_th, String type_detailse_en, double type_price, int type_adult_limit, int type_children_limit, int type_total_rooms) {
        this.type_id = type_id;
        this.type_name_th = type_name_th;
        this.type_name_en = type_name_en;
        this.type_details_th = type_details_th;
        this.type_details_en = type_detailse_en;
        this.type_price = type_price;
        this.type_adult_limit = type_adult_limit;
        this.type_children_limit = type_children_limit;
        this.type_total_rooms = type_total_rooms;
    }

    public int getTypeId() {
        return type_id;
    }

    public void setTypeId(int type_id) {
        this.type_id = type_id;
    }

    public String getTypeNameTh() {
        return type_name_th;
    }

    public void setTypeNameTh(String type_name_th) {
        this.type_name_th = type_name_th;
    }

    public String getTypeNameEn() {
        return type_name_en;
    }

    public void setTypeNameEn(String type_name_en) {
        this.type_name_en = type_name_en;
    }

    public String getTypeDetailsTh() {
        return type_details_th;
    }

    public void setTypeDetailsTh(String type_details_th) {
        this.type_details_th = type_details_th;
    }

    public String getTypeDetailsEn() {
        return type_details_en;
    }

    public void setTypeDetailsEn(String type_detailse_en) {
        this.type_details_en = type_detailse_en;
    }

    public double getTypePrice() {
        return type_price;
    }

    public void setTypePrice(double type_price) {
        this.type_price = type_price;
    }

    public int getTypeAdultLimit() {
        return type_adult_limit;
    }

    public void setTypeAdultLimit(int type_adult_limit) {
        this.type_adult_limit = type_adult_limit;
    }

    public int getTypeChildrenLimit() {
        return type_children_limit;
    }

    public void setTypeChildrenLimit(int type_children_limit) {
        this.type_children_limit = type_children_limit;
    }

    public int getTypeTotalRooms() {
        return type_total_rooms;
    }

    public void setTypeTotalRooms(int type_total_rooms) {
        this.type_total_rooms = type_total_rooms;
    }
}
