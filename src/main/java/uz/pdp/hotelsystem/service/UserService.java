package uz.pdp.hotelsystem.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import uz.pdp.hotelsystem.entity.User;
import uz.pdp.hotelsystem.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class UserService  {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Foydalanuvchini email orqali olish
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email); // userRepository'da findByEmail metodini qo'shish kerak
    }

    // Foydalanuvchini reset tokeni bilan olish
    public User getUserByResetToken(String token) {
        return userRepository.findByResetToken(token); // userRepository'da findByResetToken metodini qo'shish kerak
    }

    // Foydalanuvchining parolini yangilash
    public boolean updateUserPassword(User user, String newPassword) {
        if (user == null) {
            return false;
        }

        user.setPassword(passwordEncoder.encode(newPassword)); // Yangi parolni shifrlash
        user.setResetToken(null); // Tokenni tozalash
        user.setTokenExpirationDate(null); // Tokenning muddati tugadi
        userRepository.save(user); // Foydalanuvchini saqlash
        return true;
    }

    // Foydalanuvchiga yangi reset tokenini o'rnatish
    public void setResetToken(User user, String token) {
        user.setResetToken(token);
        user.setTokenExpirationDate(LocalDateTime.now().plusHours(1)); // Tokenning muddati 1 soat
        userRepository.save(user);
    }

}
