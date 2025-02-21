package uz.pdp.hotelsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.pdp.hotelsystem.entity.tempAbs.AbsLongEntity;
import uz.pdp.hotelsystem.enums.PaymentMethod;

import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Payment extends AbsLongEntity {

    @OneToOne
    private BookingRoom bookingRoom;

    private Long amount;

    private Timestamp paymentDate;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
}
