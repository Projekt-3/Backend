package org.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShowDTO {
    private int Id;
    private String name;
    private Date startDate;
    private Date endDate;
    private List<Integer> employeeIds;
    private List<Integer> shiftIds;
}
