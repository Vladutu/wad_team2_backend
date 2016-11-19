package ro.ucv.ace.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ucv.ace.service.IMailService;
import ro.ucv.ace.utils.mail.IMail;
import ro.ucv.ace.utils.mail.IMailSender;
import ro.ucv.ace.utils.mail.impl.NewAccountMail;

/**
 * Created by Geo on 19.11.2016.
 */
@Service("mailService")
public class MailService implements IMailService {

    @Autowired
    private IMailSender mailSender;


    @Override
    public void sendAccountCreationMail(String to, String username, String password) {
        IMail mail = new NewAccountMail(to, username, password);
        mailSender.sendMail(mail);
    }
}
