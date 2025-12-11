package org.example.backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstname;
    private String lastname;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String mail;

    @Column(unique = true)
    private Integer phone;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    // ---------------------
    // ManyToMany with Shift
    // ---------------------
    @ManyToMany
    @JoinTable(
            name = "employee_shift",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "shift_id")
    )
    private List<Shift> shifts;

    @ManyToMany(mappedBy = "employees")
    private List<Show> shows;
}
