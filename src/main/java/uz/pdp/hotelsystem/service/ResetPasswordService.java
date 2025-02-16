package uz.pdp.hotelsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.hotelsystem.entity.PasswordResetToken;
import uz.pdp.hotelsystem.entity.User;
import uz.pdp.hotelsystem.repository.PasswordResetTokenRepository;
import uz.pdp.hotelsystem.repository.UserRepository;
import uz.pdp.hotelsystem.securty.EmailService;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ResetPasswordService {

    private final UserRepository userRepository;
    private final PasswordResetTokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public void requestPasswordReset(String email) {
        try {
            // Foydalanuvchini topish
            User user = userRepository.findUserByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Avvalgi tokenni topish va o'chirish
            Optional<PasswordResetToken> existingToken = tokenRepository.findByUser(user);
            if (existingToken.isPresent()) {
                tokenRepository.delete(existingToken.get());
            }

            // Yangi token yaratish
            PasswordResetToken token = new PasswordResetToken(user);
            tokenRepository.save(token);
            System.out.println(token);

            // Emailga token yuborish
            emailService.sendResetPasswordEmail(user.getEmail(), token.getToken());
        } catch (RuntimeException e) {
            // Xatolikni logga yozish yoki foydalanuvchiga xabar berish
            System.err.println("Xatolik yuz berdi: " + e.getMessage());
            throw new RuntimeException("Parolni tiklash so'rovi amalga oshirilmadi. Iltimos, qayta urinib ko'ring.");
        }
    }

    public void resetPassword(String token, String newPassword) {
        // Tokenni topish
        PasswordResetToken resetToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        // Tokenning amal qilish muddatini tekshirish
        if (resetToken.getExpirationDate().before(new Date())) {
            throw new RuntimeException("Token has expired");
        }

        // Yangi parolni saqlash
        User user = resetToken.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        // Tokenni o'chirish
        tokenRepository.delete(resetToken);
    }

}
