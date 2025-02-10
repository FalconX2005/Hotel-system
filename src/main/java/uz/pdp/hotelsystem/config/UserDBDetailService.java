package uz.pdp.hotelsystem.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.pdp.hotelsystem.entity.User;
import uz.pdp.hotelsystem.repository.UserRepository;

import java.util.Optional;

@Service
public class UserDBDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDBDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> optionalUser = userRepository.getUserByUsername(username);

        optionalUser.orElseThrow(() -> new UsernameNotFoundException(username));
        return optionalUser.get();

    }
}
