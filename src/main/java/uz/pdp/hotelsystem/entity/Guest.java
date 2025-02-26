package uz.pdp.hotelsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import uz.pdp.hotelsystem.entity.tempAbs.AbsLongEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SQLDelete(sql = "UPDATE guest SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Guest extends AbsLongEntity {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true)
    @Pattern(regexp = "^\\+998\\d{9}$")
    private String phoneNumber;



}
