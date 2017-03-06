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

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getType_name_th() {
        return type_name_th;
    }

    public void setType_name_th(String type_name_th) {
        this.type_name_th = type_name_th;
    }

    public String getType_name_en() {
        return type_name_en;
    }

    public void setType_name_en(String type_name_en) {
        this.type_name_en = type_name_en;
    }

    public String getType_details_th() {
        return type_details_th;
    }

    public void setType_details_th(String type_details_th) {
        this.type_details_th = type_details_th;
    }

    public String getType_details_en() {
        return type_details_en;
    }

    public void setType_details_en(String type_detailse_en) {
        this.type_details_en = type_detailse_en;
    }

    public double getType_price() {
        return type_price;
    }

    public void setType_price(double type_price) {
        this.type_price = type_price;
    }

    public int getType_adult_limit() {
        return type_adult_limit;
    }

    public void setType_adult_limit(int type_adult_limit) {
        this.type_adult_limit = type_adult_limit;
    }

    public int getType_children_limit() {
        return type_children_limit;
    }

    public void setType_children_limit(int type_children_limit) {
        this.type_children_limit = type_children_limit;
    }

    public int getType_total_rooms() {
        return type_total_rooms;
    }

    public void setType_total_rooms(int type_total_rooms) {
        this.type_total_rooms = type_total_rooms;
    }
}
