package uz.pdp.hotelsystem.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hotelsystem.entity.User;
import uz.pdp.hotelsystem.payload.LoginDTO;
import uz.pdp.hotelsystem.payload.SignUpDTO;
import uz.pdp.hotelsystem.repository.UserRepository;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

//    @GetMapping("/login")
//    public

    @PostMapping("/login")
    public ApiResult<?> login(@RequestBody LoginDTO loginDTO,
                              HttpServletRequest request) {
        Optional<User> userByUsername = userRepository.getUserByUsername(loginDTO.getUsername());
        if(!userByUsername.isPresent()){
            return ApiResult.error("User not found");
        }
        User user = userByUsername.get();
        if(!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())){
            return ApiResult.error("Wrong password");
        }
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user,
                null,
                user.getAuthorities()
        );


        securityContext.setAuthentication(authentication);

        HttpSession session = request.getSession();

        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

//        return "success";
        return ApiResult.success("User logged in");


    }

//    @PostMapping("/sign-up")
//    public String signUp(@RequestBody SignUpDTO singUpDTO, HttpServletRequest request) {
//
//                Optional<User> optionalUser = userRepository.getUserByUsername(singUpDTO.getUsername());
//                if (optionalUser.isPresent()) {
//                    throw new RuntimeException("Username is already exist");
//                }
//             String encodedPassword = passwordEncoder.encode(singUpDTO.getPassword());
//
//                User user = new User();
//                user.setUsername(singUpDTO.getUsername());
//                user.setPassword(encodedPassword);
//
//                user.setRole(singUpDTO.getRole());
//                userRepository.save(user);
//
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//
//        Authentication authentication = new UsernamePasswordAuthenticationToken(
//                user,
//                null,
//                user.getAuthorities()
//        );
//
//        securityContext.setAuthentication(authentication);
//
//        HttpSession session = request.getSession();
//
//        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
//
//
//        return "success";
//    }

}
