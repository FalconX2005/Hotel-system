package uz.pdp.hotelsystem.payload;

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
    private Integer id;
    private String username;
    private String password;
    private String email;
    private RoleEnum role;
}