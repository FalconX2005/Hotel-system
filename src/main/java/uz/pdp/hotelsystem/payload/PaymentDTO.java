package uz.pdp.hotelsystem.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.hotelsystem.enums.PaymentMethod;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDTO {

    private Integer id;

    private Integer bookingRoomId;

    private Long amount;

    private Timestamp paymentDate;


    private PaymentMethod paymentMethod;
}
