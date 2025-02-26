package uz.pdp.hotelsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import uz.pdp.hotelsystem.entity.tempAbs.AbsLongEntity;
import uz.pdp.hotelsystem.enums.PaymentMethod;

import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SQLDelete(sql = "UPDATE payment SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Payment extends AbsLongEntity {

    @OneToOne
    private BookingRoom bookingRoom;


    private Long amount;

    private Timestamp paymentDate;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
}
