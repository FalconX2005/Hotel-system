package uz.pdp.hotelsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hotelsystem.payload.LoginDTO;
import uz.pdp.hotelsystem.payload.ResetPasswordTokenDTO;
import uz.pdp.hotelsystem.payload.SignUpDTO;
import uz.pdp.hotelsystem.securty.AuthService;
import uz.pdp.hotelsystem.service.ResetPasswordService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final ResetPasswordService resetPasswordService;

    @PostMapping("/login")
    public Object login(@RequestBody LoginDTO loginDTO) {
        return authService.login(loginDTO);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/sign-up")
    public Object signUp(@RequestBody SignUpDTO signUpDTO) {
        return authService.signUp(signUpDTO);
    }

    @PostMapping("/request-reset")
    public void requestPasswordReset(@RequestParam String email) {
        resetPasswordService.requestPasswordReset(email);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String token,  @RequestBody ResetPasswordTokenDTO tokenDTO) {
        try {
            resetPasswordService.resetPassword(tokenDTO.getToken(), tokenDTO.getNewPassword());
            return ResponseEntity.ok("Password reset successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }




}
