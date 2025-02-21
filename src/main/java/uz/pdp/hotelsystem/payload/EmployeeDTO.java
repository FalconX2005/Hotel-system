package uz.pdp.hotelsystem.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.hotelsystem.entity.User;
import uz.pdp.hotelsystem.enums.RoleEnum;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * DTO for {@link uz.pdp.hotelsystem.entity.Employee}
 */

@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Timestamp workTime;

    private String username;

    private String password;

    private RoleEnum role;
}
