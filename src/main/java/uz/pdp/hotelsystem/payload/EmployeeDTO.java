package uz.pdp.hotelsystem.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private Timestamp workTime;
    private RoleEnum role;

}
