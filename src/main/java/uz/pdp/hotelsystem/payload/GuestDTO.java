package uz.pdp.hotelsystem.payload;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GuestDTO {

    private Long id;

    @NotNull
    @NotBlank
    private String firstName;


    @NotNull
    @NotBlank
    private String lastName;


    @NotNull
    @NotBlank
    private String phoneNumber;


}