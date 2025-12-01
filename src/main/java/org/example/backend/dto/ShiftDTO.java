package org.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShiftDTO {
    private int Id;
    private int showId;
    private LocalTime checkIn;
    private LocalTime checkOut;
    private List<Integer> employeeIds;
}
