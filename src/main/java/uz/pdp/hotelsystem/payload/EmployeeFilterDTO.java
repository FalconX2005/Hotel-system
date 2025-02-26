package uz.pdp.hotelsystem.payload;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.hotelsystem.entity.User;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeFilterDTO {

    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    private Integer age;

    private Timestamp workTime;

}
