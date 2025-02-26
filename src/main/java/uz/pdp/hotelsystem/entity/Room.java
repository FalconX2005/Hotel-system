package uz.pdp.hotelsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import uz.pdp.hotelsystem.entity.tempAbs.AbsDateEntity;
import uz.pdp.hotelsystem.entity.tempAbs.AbsLongEntity;
import uz.pdp.hotelsystem.enums.RoomType;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@SQLDelete(sql = "UPDATE room SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Room extends AbsLongEntity {

    @Column(unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    private RoomType type;

    private Long price;

    private Boolean is_available;

    @ManyToOne
    private Hotel hotel;

}
