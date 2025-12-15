package org.example.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
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
    private LocalTime checkIn;
    private LocalTime checkOut;

    // ---------------------
    // ManyToOne with Show
    // ---------------------
    @ManyToOne
    @JoinColumn(name = "show_id")
    @JsonBackReference
    private Show show;

    // ---------------------
    // ManyToMany with Employee (inverse side)
    // ---------------------
    @ManyToMany(mappedBy = "shifts")
    private List<Employee> employees;
}
