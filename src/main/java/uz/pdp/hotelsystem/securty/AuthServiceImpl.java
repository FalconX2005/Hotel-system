package uz.pdp.hotelsystem.securty;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.hotelsystem.entity.User;
import uz.pdp.hotelsystem.payload.LoginDTO;
import uz.pdp.hotelsystem.payload.SignUpDTO;
import uz.pdp.hotelsystem.repository.UserRepository;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public String login(LoginDTO loginDTO) {
        User user = userRepository.getUserByUsername(loginDTO.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(loginDTO.getUsername()));
        boolean matches = passwordEncoder.matches(loginDTO.getPassword(), user.getPassword());

        if (!matches)
            throw new AccessDeniedException("Invalid username or password");

        return jwtProvider.generateToken(user.getUsername(), new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000));

    }

    /*@Override
    public String signUp(SignUpDTO signUpDTO) {
        Optional<User> optionalUser = userRepository.getUserByUsername(signUpDTO.getUsername());
        if (optionalUser.isPresent())
            throw new AccessDeniedException("Username is already in use");

        User user = new User();
        user.setUsername(signUpDTO.getUsername());
        user.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
        user.setEmail(signUpDTO.getEmail());
        user.setRole(signUpDTO.getRole());

        userRepository.save(user);

        return "User registered successfully!";
    }*/


}
