package reservation;

/**
 * Created by Adisorn on 1/3/2560.
 */
public class Reservation {

    private int reservation_id;
    private String reservation_date;
    private int reservation_night;
    private int reservation_adult;
    private int reservation_children;
    private int room_type;
    private int reservation_status;
    private int reservation_payment_type;

    private String customer_title_name;
    private String customer_full_name;
    private String customer_email;
    private String customer_tel;
    private String customer_country;
    private String customer_nation;

    private String credit_card_id;
    private String credit_card_expired_date;
    private String credit_card_cvv;

    public Reservation(int reservation_id, String reservation_date, int reservation_night, int reservation_adult,
                       int reservation_children, int reservation_payment_type, String customer_title_name,
                       String customer_full_name, String customer_email, String customer_tel, String customer_country,
                       String customer_nation, String credit_card_id, String credit_card_expired_date, String credit_card_cvv) {
        this.reservation_id = reservation_id;
        this.reservation_date = reservation_date;
        this.reservation_night = reservation_night;
        this.reservation_adult = reservation_adult;
        this.reservation_children = reservation_children;
        this.room_type = room_type;
        this.reservation_payment_type = reservation_payment_type;
        this.reservation_status = 0;
        this.customer_title_name = customer_title_name;
        this.customer_full_name = customer_full_name;
        this.customer_email = customer_email;
        this.customer_tel = customer_tel;
        this.customer_country = customer_country;
        this.customer_nation = customer_nation;
        this.credit_card_id = credit_card_id;
        this.credit_card_expired_date = credit_card_expired_date;
        this.credit_card_cvv = credit_card_cvv;
    }

    public Reservation() {

    }

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public String getReservation_date() {
        return reservation_date;
    }

    public void setReservation_date(String reservation_date) {
        this.reservation_date = reservation_date;
    }

    public int getReservation_night() {
        return reservation_night;
    }

    public void setReservation_night(int reservation_night) {
        this.reservation_night = reservation_night;
    }

    public int getReservation_adult() {
        return reservation_adult;
    }

    public void setReservation_adult(int reservation_adult) {
        this.reservation_adult = reservation_adult;
    }

    public int getReservation_children() {
        return reservation_children;
    }

    public void setReservation_children(int reservation_children) {
        this.reservation_children = reservation_children;
    }

    public int getRoom_type() {
        return room_type;
    }

    public void setRoom_type(int room_type) {
        this.room_type = room_type;
    }

    public int getReservation_status() {
        return reservation_status;
    }

    public void setReservation_status(int reservation_status) {
        this.reservation_status = reservation_status;
    }

    public int getReservation_payment_type() {
        return reservation_payment_type;
    }

    public void setReservation_payment_type(int reservation_payment_type) {
        this.reservation_payment_type = reservation_payment_type;
    }

    public String getCustomer_title_name() {
        return customer_title_name;
    }

    public void setCustomer_title_name(String customer_title_name) {
        this.customer_title_name = customer_title_name;
    }

    public String getCustomer_full_name() {
        return customer_full_name;
    }

    public void setCustomer_full_name(String customer_full_name) {
        this.customer_full_name = customer_full_name;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getCustomer_tel() {
        return customer_tel;
    }

    public void setCustomer_tel(String customer_tel) {
        this.customer_tel = customer_tel;
    }

    public String getCustomer_country() {
        return customer_country;
    }

    public void setCustomer_country(String customer_country) {
        this.customer_country = customer_country;
    }

    public String getCustomer_nation() {
        return customer_nation;
    }

    public void setCustomer_nation(String customer_nation) {
        this.customer_nation = customer_nation;
    }

    public String getCredit_card_id() {
        return credit_card_id;
    }

    public void setCredit_card_id(String credit_card_id) {
        this.credit_card_id = credit_card_id;
    }

    public String getCredit_card_expired_date() {
        return credit_card_expired_date;
    }

    public void setCredit_card_expired_date(String credit_card_expired_date) {
        this.credit_card_expired_date = credit_card_expired_date;
    }

    public String getCredit_card_cvv() {
        return credit_card_cvv;
    }

    public void setCredit_card_cvv(String credit_card_cvv) {
        this.credit_card_cvv = credit_card_cvv;
    }

}
