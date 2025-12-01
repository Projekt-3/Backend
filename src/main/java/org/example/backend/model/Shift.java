package org.example.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Shift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private LocalTime checkIn;
    private LocalTime checkOut;

    @ManyToOne
    @JoinColumn(name = "showId")
    private Show show;

    @ManyToMany
    @JoinTable(
            name = "Employee_Shift",
            joinColumns = @JoinColumn(name = "ShiftId"),
            inverseJoinColumns = @JoinColumn(name = "EmployeeId")
    )
    private List<Employee> employees;
}
