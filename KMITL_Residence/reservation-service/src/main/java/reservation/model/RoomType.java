package reservation.model;

/**
 * Created by Adisorn on 6/3/2560.
 */
public class RoomType {

    private int typeId;
    private int typeTotalRooms;
    private String typeNameTh;
    private String typeNameEn;
    private String typeDetailsTh;
    private String typeDetailsEn;
    private double typePrice;
    private int typeAdultLimit;
    private int typeChildrenLimit;


    public RoomType(int typeId, String typeNameTh, String typeNameEn, String typeDetailsTh, String typeDetailsEn,
                    double typePrice, int typeAdultLimit, int typeChildrenLimit, int typeTotalRooms) {
        this.typeId = typeId;
        this.typeNameTh = typeNameTh;
        this.typeNameEn = typeNameEn;
        this.typeDetailsTh = typeDetailsTh;
        this.typeDetailsEn = typeDetailsEn;
        this.typePrice = typePrice;
        this.typeAdultLimit = typeAdultLimit;
        this.typeChildrenLimit = typeChildrenLimit;
        this.typeTotalRooms = typeTotalRooms;
    }

    public RoomType() {

    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeNameTh() {
        return typeNameTh;
    }

    public void setTypeNameTn(String typeNameTh) {
        this.typeNameTh = typeNameTh;
    }

    public String getTypeNameEn() {
        return typeNameEn;
    }

    public void setTypeNameEn(String typeNameEn) {
        this.typeNameEn = typeNameEn;
    }

    public String getTypeDetailsTh() {
        return typeDetailsTh;
    }

    public void setTypeDetailsTh(String typeDetailsTh) {
        this.typeDetailsTh = typeDetailsTh;
    }

    public String getTypeDetailsEn() {
        return typeDetailsEn;
    }

    public void setTypeDetailsEn(String typeDetailsEn) {
        this.typeDetailsEn = typeDetailsEn;
    }

    public double getTypePrice() {
        return typePrice;
    }

    public void setTypePrice(double typePrice) {
        this.typePrice = typePrice;
    }

    public int getTypeAdultLimit() {
        return typeAdultLimit;
    }

    public void setTypeAdultLimit(int typeAdultLimit) {
        this.typeAdultLimit = typeAdultLimit;
    }

    public int getTypeChildrenLimit() {
        return typeChildrenLimit;
    }

    public void setTypeChildrenLimit(int typeChildrenLimit) {
        this.typeChildrenLimit = typeChildrenLimit;
    }

    public int getTypeTotalRooms() {
        return typeTotalRooms;
    }

    public void setTypeTotalRooms(int typeTotalRooms) {
        this.typeTotalRooms = typeTotalRooms;
    }
}
