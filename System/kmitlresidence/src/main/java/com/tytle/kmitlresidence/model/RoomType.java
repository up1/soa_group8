package com.tytle.kmitlresidence.model;

public class RoomType {

    private int room_type_id, maximum_guest, price;
    private String name, description;

    public RoomType() {

    }

    public int getRoom_type_id() {
        return room_type_id;
    }

    public void setRoom_type_id(int room_type_id) {
        this.room_type_id = room_type_id;
    }

    public int getMaximum_guest() {
        return maximum_guest;
    }

    public void setMaximum_guest(int maximum_guest) {
        this.maximum_guest = maximum_guest;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}