package uz.pdp.hotelsystem.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.hotelsystem.enums.RoomType;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomDTO {

    private Integer id;

    private String name;

    private RoomType type;

    private Long price;

    private Boolean is_available;

    private Integer hotelId;


}
