package org.example.backend.dto;
import java.time.LocalTime;

public record ShiftDTO(
    Integer id,
    LocalTime plannedStart,
    LocalTime plannedEnd,
    Integer showId,
    String showTitle
    //private List<Integer> employeeIds;
) {}
