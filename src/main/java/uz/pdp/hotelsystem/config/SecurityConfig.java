package uz.pdp.hotelsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(conf -> conf.disable());

        http.userDetailsService(userDetailsService);

        http.authorizeHttpRequests(conf -> conf


                        .requestMatchers(
                                "/auth/login",
//                        "auth/sign-up",
                                "/**.html"
//                        "/open",
//                        "/**"
                        )
                        .permitAll()
                        .anyRequest()
                        .authenticated()
        );


        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//
//        String encodedPassword = passwordEncoder.encode("123");
//
//        System.out.println("password: " + encodedPassword);
//
//        UserDetails user = User
//                .withUsername("john")
//                .password(encodedPassword)
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User
//                .withUsername("admin")
//                .password(encodedPassword)
//                .roles("ADMIN")
//                .build();
//
//        UserDetailsService userDetailsService = new InMemoryUserDetailsManager(user, admin);
//        return userDetailsService;
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
