package uz.pdp.hotelsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.pdp.hotelsystem.enums.RoleEnum;

import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private Integer age;
    private Timestamp workTime;

    @ManyToOne
    private User user;



}
