package uz.pdp.hotelsystem.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.hotelsystem.enums.RoleEnum;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignUpDTO {
    private Integer id;

    private String username;

    private String password;


    private RoleEnum role;


}
