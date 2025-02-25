package uz.pdp.hotelsystem.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.hotelsystem.entity.User;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeFilterDTO {
    private String firstName;
    private String lastName;
    private Integer age;

    private Timestamp workTime;

}
