package uz.pdp.hotelsystem.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hotelsystem.entity.User;
import uz.pdp.hotelsystem.payload.EmailDTO;
import uz.pdp.hotelsystem.payload.LoginDTO;
import uz.pdp.hotelsystem.payload.ResetPasswordDTO;
import uz.pdp.hotelsystem.payload.SignUpDTO;
import uz.pdp.hotelsystem.repository.UserRepository;
import uz.pdp.hotelsystem.service.PasswordResetService;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PasswordResetService passwordResetService;

    public AuthController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          PasswordResetService passwordResetService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.passwordResetService = passwordResetService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO) {
        // Foydalanuvchini tekshirish va autentifikatsiya qilish
        Optional<User> user = userRepository.getUserByUsername(loginDTO.getUsername());
        if (user.isPresent()) {
            if (passwordEncoder.matches(loginDTO.getPassword(), user.get().getPassword())) {
                // Login muvaffaqiyatli bo'lsa, boshqa sahifaga yo'naltirish
                return "redirect:/home"; // Home sahifasiga yo'naltiradi
            }
        }
        return "redirect:/auth/login?error=true"; // Xatolik yuzaga kelsa, login sahifasiga qaytaradi
    }

    @GetMapping("/home")
    public String homePage() {
        return "Home Page"; // Yangi sahifa yoki ma'lumot ko‘rsatish
    }


    // Parolni tiklash uchun email yuborish
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody EmailDTO emailDTO) {
        passwordResetService.sendPasswordResetEmail(emailDTO.getEmail());
        return ResponseEntity.ok("Parolni tiklash havolasi yuborildi");
    }

    // Parolni tiklash
    @PostMapping("/reset-password/{token}")
    public ResponseEntity<String> resetPassword(@PathVariable String token, @RequestBody ResetPasswordDTO resetPasswordDTO) {
        boolean success = passwordResetService.resetPassword(token, resetPasswordDTO.getNewPassword());
        if (success) {
            return ResponseEntity.ok("Parol muvaffaqiyatli yangilandi");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token muddati tugagan yoki noto'g'ri");
        }
    }


}
