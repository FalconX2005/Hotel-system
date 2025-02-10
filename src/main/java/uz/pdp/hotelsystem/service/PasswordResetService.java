package uz.pdp.hotelsystem.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.hotelsystem.entity.User;
import uz.pdp.hotelsystem.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@Service
public class PasswordResetService {

    private final JavaMailSender mailSender;
    private final UserService userService;

    public PasswordResetService(JavaMailSender mailSender, UserRepository userRepository, UserService userService) {
        this.mailSender = mailSender;
        this.userService = userService;
    }

    public void sendPasswordResetEmail(String email) {
        Optional<User> userOpt = userService.getUserByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            String token = UUID.randomUUID().toString(); // Token yaratish
            userService.setResetToken(user, token); // Tokenni foydalanuvchiga qo‘shish

            // Parolni tiklash havolasini emailga yuborish
            String resetUrl = "http://localhost:8080/auth/reset-password/" + token;
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setSubject("Parolni tiklash so'rovi");
            message.setText("Parolni tiklash uchun quyidagi havolani bosing: " + resetUrl);
            mailSender.send(message);
        }
    }

    public boolean resetPassword(String token, String newPassword) {
        User user = userService.getUserByResetToken(token);
        if (user != null && user.getTokenExpirationDate().isAfter(LocalDateTime.now())) {
            return userService.updateUserPassword(user, newPassword);
        }
        return false;
    }

}
