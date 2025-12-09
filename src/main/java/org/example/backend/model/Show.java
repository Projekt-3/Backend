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
@Table(name = "shows") // rename table to 'shows'
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private Date startDate;
    private Date endDate;

    // ---------------------
    // OneToMany with Shift
    // ---------------------
    @OneToMany(mappedBy = "show")
    private List<Shift> shifts;
}
