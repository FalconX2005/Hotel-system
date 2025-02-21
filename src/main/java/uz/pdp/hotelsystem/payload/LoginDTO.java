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
public class LoginDTO implements Serializable {

    private String username;
    private String password;

}
