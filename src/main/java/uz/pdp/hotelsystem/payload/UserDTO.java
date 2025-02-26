package uz.pdp.hotelsystem.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.hotelsystem.enums.RoleEnum;

import java.io.Serializable;

/**
 * DTO for {@link uz.pdp.hotelsystem.entity.User}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {
    private Long id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private RoleEnum role;
}