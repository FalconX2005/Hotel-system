package uz.pdp.hotelsystem.securty;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
public class EmailService {

    @Value("${reset-password}")
    private String resetPasswordUrl;

    private final JavaMailSender mailSender;

    public void sendResetPasswordEmail(String email, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setFrom("email@gmail.com");
        message.setSubject("Reset Password");
        message.setText("Reset password link to: \n" +
                "http://localhost:8080/auth/reset-password?token=" + token);
        mailSender.send(message);
    }
}
