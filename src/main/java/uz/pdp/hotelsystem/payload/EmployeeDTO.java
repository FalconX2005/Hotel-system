package uz.pdp.hotelsystem.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.hotelsystem.entity.User;
import uz.pdp.hotelsystem.enums.RoleEnum;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * DTO for {@link uz.pdp.hotelsystem.entity.Employee}
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTO implements Serializable {

    private Long id;

    @NotNull
    @NotBlank
    private String firstName;


    @NotNull
    @NotBlank
    private String lastName;

    @NotNull
    @Past
    private LocalDate birthDate;

    @NotBlank
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime startTime ;

    @NotBlank
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime endTime ;

    @NotBlank
    @NotNull
    private String username;

    @NotBlank
    @NotNull
    private String password;

    @NotBlank
    private RoleEnum role;
}
