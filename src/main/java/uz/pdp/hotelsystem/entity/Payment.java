package uz.pdp.hotelsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.pdp.hotelsystem.enums.PaymentMethod;

import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private BookingRoom bookingRoom;

    private Long amount;

    private Timestamp paymentDate;


    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
}
