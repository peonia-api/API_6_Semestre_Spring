package fatec.sp.gov.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendPasswordResetEmail(String to, String token) {
        String url = "http://localhost:8080/reset-password?token=" + token;
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(to);
        email.setSubject("Password Reset Request");
        email.setText("To reset your password, click the link below:\n" + url);
        mailSender.send(email);
    }
}
