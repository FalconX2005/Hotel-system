package uz.pdp.hotelsystem.entity;

import jakarta.persistence.*;
import lombok.*;


import java.sql.Timestamp;

@Entity(name = "hotel_employee")
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
