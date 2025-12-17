package org.example.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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
    private Integer id;

    private LocalTime plannedStart;
    private LocalTime plannedEnd;
    private LocalDate date;


    // ---------------------
    // ManyToOne with Show
    // ---------------------
    @ManyToOne
    @JoinColumn(name = "show_id")
    @JsonBackReference(value = "show-shifts")
    private Show show;

    // ---------------------
    // ManyToMany with Employee (inverse side)
    // ---------------------
    @OneToMany(mappedBy = "shift")
    @JsonManagedReference(value = "shift-employeeShift")
    private List<EmployeeShift> employeeShifts;
}
