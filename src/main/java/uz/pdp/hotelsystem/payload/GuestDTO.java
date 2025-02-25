package uz.pdp.hotelsystem.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GuestDTO {

    private Long id;

    private String firstName;


    private String lastName;

    private String phoneNumber;


}