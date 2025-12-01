package org.example.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Date startDate;
    private Date endDate;

    @ManyToMany
    @JoinTable(name = "Employee_Show",
            joinColumns = @JoinColumn(name = "showId"),
            inverseJoinColumns = @JoinColumn(name = "employeeId"))
    private List<Employee> employees;

    @OneToMany(mappedBy = "show")
    private List<Shift> shifts;
}
