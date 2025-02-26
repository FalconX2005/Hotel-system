package uz.pdp.hotelsystem.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.hotelsystem.enums.RoomType;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomDTO {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private RoomType type;

    @NotNull
    private Long price;

    @NotNull
    private Boolean is_available;

    @NotNull
    private Long hotelId;


}