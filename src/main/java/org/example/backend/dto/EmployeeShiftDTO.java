package org.example.backend.dto;

import org.example.backend.model.EmployeeShift;
import org.example.backend.model.Shift;

public record EmployeeShiftDTO(
        Integer id,
        boolean checkInStatus,
        ShiftDTO shift
) {
    public static EmployeeShiftDTO fromEntity(EmployeeShift es) {
        Shift s = es.getShift();

        return new EmployeeShiftDTO(
                es.getId(),
                es.getCheckInStatus(),
                new ShiftDTO(
                        s.getId(),
                        s.getDate(),
                        s.getPlannedStart(),
                        s.getPlannedEnd(),
                        s.getShow() != null ? s.getShow().getId() : null,
                        s.getShow() != null ? s.getShow().getTitle() : null
                )
        );
    }
}
