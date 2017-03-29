package email.model;

/**
 * Created by LAB207_43 on 14/3/2560.
 */
public class Email {

    private int emailType;
    private String destination;
    private String titleName;
    private String fullName;
    private Content content;

    public void Email() {

    }

    public void Email(int emailType, String destination, String titleName, String fullName, Content content) {
        this.emailType = emailType;
        this.destination = destination;
        this.titleName = titleName;
        this.fullName = fullName;
        this.content = content;
    }

    public int getEmailType() {
        return emailType;
    }

    public void setEmailType(int emailType) {
        this.emailType = emailType;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
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

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
}
