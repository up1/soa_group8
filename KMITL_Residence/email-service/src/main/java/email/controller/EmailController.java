package email.controller;

import email.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import email.repository.EmailRepository;

import java.io.UnsupportedEncodingException;

/**
 * Created by Akkapon on 13/3/2560.
 */
@RestController
@CrossOrigin(origins = "*")
public class EmailController {

    private final EmailRepository emailRepository;

    @Autowired
    public EmailController(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    @RequestMapping(value = "/sendmail",method = RequestMethod.POST)
    public String sendEmail(@RequestBody Email email_info) {
        if(email_info != null) {
            try {
                emailRepository.sendEmail(email_info);
            } catch(UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            return "Something went wrong!";
        }
        return "Mail Sent!";
    }

}