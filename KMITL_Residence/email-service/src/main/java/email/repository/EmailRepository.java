package email.repository;

import com.google.common.collect.Lists;
import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * Created by Akkapon on 14/3/2560.
 */

@Repository
public class EmailRepository {

    private static String sender = "kmitlresidence@gmail.com";
    private static String sender_name = "KMITL Residence";

    @Autowired
    public EmailService emailService;

    public void sendEmail(email.model.Email email_info) throws UnsupportedEncodingException {
        final Email email = DefaultEmail.builder()
                .from(new InternetAddress(sender, sender_name))
                .to(Lists.newArrayList(new InternetAddress(email_info.getDestination(), email_info.getFullName())))
                .subject(fillSubject(email_info.getEmailType()))
                .body(fillBody(email_info))
                .encoding(String.valueOf(Charset.forName("UTF-8"))).build();
        emailService.send(email);
    }

    public String fillSubject(int emailType) {
        switch(emailType) {
            case 1:
                return "Reservation Confirmation";
            case 2:
                return "Your Reservation has been Confirmed";
            case 3:
                return "Your Reservation has been Cancelled";
            default:
                return null;
        }
    }

    public String fillBody(email.model.Email email_info) {
        switch (email_info.getEmailType()) {
            case 1:
                return "Thanks "+email_info.getTitleName()+email_info.getFullName()+" for making a reservation. " +
                        "To confirm and complete a reservation, please click this link " +
                        email_info.getContent().getConfirmationLink() + ", to cancel, please click this link " +
                        email_info.getContent().getCancelLink(); // TODO -> add more details
            case 2:
                return "Thanks a lot for choosing our service. " +
                        "Our staff will provide you the best customer service you only deserve. Enjoy your day!";
            case 3:
                return "Your reservation has been cancelled, " +
                        "anyway, We’d love to see you again next time. Have a nice day!";
            default:
                return null;
        }
    }

}
