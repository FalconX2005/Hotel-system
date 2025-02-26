package uz.pdp.hotelsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import uz.pdp.hotelsystem.entity.tempAbs.AbsLongEntity;
import uz.pdp.hotelsystem.enums.StatusBooking;


import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SQLDelete(sql = "UPDATE booking_room SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
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
