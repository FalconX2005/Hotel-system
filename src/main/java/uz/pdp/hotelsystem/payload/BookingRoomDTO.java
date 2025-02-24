package uz.pdp.hotelsystem.payload;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.hotelsystem.enums.StatusBooking;

import java.sql.Timestamp;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingRoomDTO {

    private Long id;

    private Long guestId;

    private Long roomId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Timestamp check_in_date;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Timestamp check_out_date;

    private StatusBooking status;

}
