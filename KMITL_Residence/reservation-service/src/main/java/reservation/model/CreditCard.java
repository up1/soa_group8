package reservation.model;

/**
 * Created by Adisorn on 10/3/2560.
 */
public class CreditCard {

    private String number;
    private String expiredDate;
    private String cvv;

    public CreditCard() {

    }

    public CreditCard(String number, String expiredDate, String cvv) {
        this.number = number;
        this.expiredDate = expiredDate;
        this.cvv = cvv;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
