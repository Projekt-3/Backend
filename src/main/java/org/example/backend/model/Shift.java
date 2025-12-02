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
    private int id;

    private LocalTime checkIn;
    private LocalTime checkOut;

    // ---------------------
    // ManyToOne with Show
    // ---------------------
    @ManyToOne
    @JoinColumn(name = "show_id")
    private Show show;

    // ---------------------
    // ManyToMany with Employee (inverse side)
    // ---------------------
    @ManyToMany(mappedBy = "shifts")
    private List<Employee> employees;
}
