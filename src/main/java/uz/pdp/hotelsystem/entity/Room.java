package uz.pdp.hotelsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.pdp.hotelsystem.entity.tempAbs.AbsDateEntity;
import uz.pdp.hotelsystem.entity.tempAbs.AbsLongEntity;
import uz.pdp.hotelsystem.enums.RoomType;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Room extends AbsLongEntity {

    private String name;

    @Enumerated(EnumType.STRING)
    private RoomType type;

    private Long price;

    private Boolean is_available;

    @ManyToOne
    private Hotel hotel;

}
