package reservation.model;

/**
 * Created by Adisorn on 10/3/2560.
 */
public class CreditCard {

    private String number;
    private String holderName;
    private String type;
    private String expiredDate;
    private String cvc;

    public CreditCard() {

    }

    public CreditCard(String number, String holderName, String type, String expiredDate, String cvc) {
        this.number = number;
        this.holderName = holderName;
        this.type = type;
        this.expiredDate = expiredDate;
        this.cvc = cvc;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }
}
