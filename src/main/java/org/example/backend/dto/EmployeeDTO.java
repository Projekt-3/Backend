package org.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeDTO {
    private int id;
    private String firstname;
    private String lastname;
    private String username;
    private String mail;
    private int phone;
    private String role;
    private List<Integer> showIds;
    private List<Integer> shiftIds;
}
