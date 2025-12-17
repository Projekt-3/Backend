package org.example.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeShift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn (name = "employee_id")
    @JsonBackReference(value = "employee-employeeShift")
    private Employee employee;

    @ManyToOne
    @JoinColumn (name = "shift_id")
    @JsonBackReference(value = "shift-employeeShift")
    private Shift shift;

    @Column(nullable = true)
    private Boolean checkInStatus;


}
