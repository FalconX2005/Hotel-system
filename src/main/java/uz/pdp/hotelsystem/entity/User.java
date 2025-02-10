package uz.pdp.hotelsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.pdp.hotelsystem.enums.RoleEnum;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    private String email; // Email qo‘shildi
    private String resetToken; // Reset token
    private LocalDateTime tokenExpirationDate; // Tokenning muddati




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }





}
