package uz.pdp.hotelsystem.payload;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotelDTO {
    private Long id;

    @NotEmpty
    private String name;


}