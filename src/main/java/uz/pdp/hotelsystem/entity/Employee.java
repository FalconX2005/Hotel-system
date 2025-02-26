package uz.pdp.hotelsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import uz.pdp.hotelsystem.entity.tempAbs.AbsLongEntity;
import uz.pdp.hotelsystem.enums.RoleEnum;


import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "hotel_employee")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SQLDelete(sql = "UPDATE hotel_employee SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Employee extends AbsLongEntity {

    private String firstName;

    private String lastName;

    private LocalDate birthdate ;


    private LocalTime startTime ;

    private LocalTime endTime ;


    @JsonIgnore
    @ManyToOne
    private User user;



}
