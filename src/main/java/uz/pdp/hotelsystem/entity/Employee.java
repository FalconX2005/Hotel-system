package uz.pdp.hotelsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import uz.pdp.hotelsystem.entity.tempAbs.AbsLongEntity;
import uz.pdp.hotelsystem.enums.RoleEnum;


import java.sql.Timestamp;

@Entity(name = "hotel_employee")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Employee extends AbsLongEntity {

    private String firstName;

    private String lastName;

    private Integer age;

    private Timestamp workTime;


    @JsonIgnore
    @ManyToOne
    private User user;



}
