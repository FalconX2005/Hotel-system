package uz.pdp.hotelsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.pdp.hotelsystem.entity.tempAbs.AbsLongEntity;
import uz.pdp.hotelsystem.enums.StatusBooking;


import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BookingRoom extends AbsLongEntity {

    @ManyToOne
    private Guest guest;

    @ManyToOne
    private Room room;

    private Timestamp check_in_date;

    private Timestamp check_out_date;

    @Enumerated(EnumType.STRING)
    private StatusBooking status;

}
