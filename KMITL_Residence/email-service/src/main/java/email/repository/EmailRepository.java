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

    @Autowired
    public EmailService emailService;

    // Test send email without template (just plain-text and fix receiver)
    public void sendEmail() throws UnsupportedEncodingException {
        final Email email = DefaultEmail.builder()
                .from(new InternetAddress("kmitlresidence@gmail.com", "KMITL Residence"))
                .to(Lists.newArrayList(new InternetAddress("autz-ii@hotmail.com", "Akkapon Chainarong")))
                .subject("Test Email Sender")
                .body("TEST MAIL")
                .encoding(String.valueOf(Charset.forName("UTF-8"))).build();
        emailService.send(email);
    }

}
