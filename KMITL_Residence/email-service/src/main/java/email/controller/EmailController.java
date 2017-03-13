package email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import email.repository.EmailRepository;

import java.io.UnsupportedEncodingException;

/**
 * Created by Akkapon on 13/3/2560.
 */
@RestController
@CrossOrigin(origins = "*")
public class EmailController {

    @Autowired
    private EmailRepository emailRepository;

    @RequestMapping("/sendmail")
    public String sendEmail() {
        try {
            emailRepository.sendEmail();
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "Mail Sent La Jaaaaa!";
    }

}