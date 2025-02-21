package uz.pdp.hotelsystem.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotelDTO {
    private Long id;

    private String name;


}