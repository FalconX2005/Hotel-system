package uz.pdp.hotelsystem.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.hotelsystem.enums.StatusBooking;

import java.sql.Timestamp;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingRoomDTO {

    private Integer id;

    private Integer guestId;


    private Integer roomId;

    private Timestamp check_in_date;

    private Timestamp check_out_date;

    private StatusBooking status;

}
