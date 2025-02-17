package uz.pdp.hotelsystem.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.hotelsystem.entity.User;
import uz.pdp.hotelsystem.enums.RoleEnum;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private Integer age;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Timestamp workTime;

    private String username;

    private String password;

    private RoleEnum role;
}
