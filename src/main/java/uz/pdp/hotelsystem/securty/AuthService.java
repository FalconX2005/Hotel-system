package uz.pdp.hotelsystem.securty;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import uz.pdp.hotelsystem.payload.LoginDTO;
import uz.pdp.hotelsystem.payload.SignUpDTO;

@Service
public interface AuthService extends UserDetailsService {


    Object login(LoginDTO loginDTO);

//    Object signUp(SignUpDTO signUpDTO);
}
