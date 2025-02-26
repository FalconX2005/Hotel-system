package uz.pdp.hotelsystem.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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


    @NotBlank
    @NotNull
    private String username;

    @NotBlank
    @NotNull
    private String password;

}
