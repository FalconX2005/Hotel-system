package uz.pdp.hotelsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import uz.pdp.hotelsystem.entity.tempAbs.AbsLongEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Hotel extends AbsLongEntity {

    private String name;

}
