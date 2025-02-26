package uz.pdp.hotelsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import uz.pdp.hotelsystem.entity.tempAbs.AbsLongEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SQLDelete(sql = "UPDATE hotel SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Hotel extends AbsLongEntity {

    @Column(nullable = false, unique = true)
    private String name;

}
