package reservation.model;

/**
 * Created by Adisorn on 10/3/2560.
 */
public class Customer {

    private String titleName;
    private String fullName;
    private String email;
    private String tel;
    private String country;
    private String nation;

    public Customer() {

    }

    public Customer(String titleName, String fullName, String email, String tel, String country, String nation) {
        this.titleName = titleName;
        this.fullName = fullName;
        this.email = email;
        this.tel = tel;
        this.country = country;
        this.nation = nation;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }
}
