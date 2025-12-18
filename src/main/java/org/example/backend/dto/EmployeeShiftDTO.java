package org.example.backend.dto;

import org.example.backend.model.EmployeeShift;
import org.example.backend.model.Shift;

public record EmployeeShiftDTO(
        Integer id,
        boolean checkInStatus,
        ShiftDTO shift
) {
    public static EmployeeShiftDTO fromEntity(EmployeeShift es) {
        Shift s = es.getShift(); // hent shift entity fra employeeShift

        return new EmployeeShiftDTO(
                es.getId(),
                es.getCheckInStatus(), // brug getCheckInStatus() som findes på EmployeeShift
                new ShiftDTO(
                        s.getId(),
                        s.getDate(),
                        s.getPlannedStart(),
                        s.getPlannedEnd(),
                        s.getShow() != null ? s.getShow().getId() : null,       // hent showId via show
                        s.getShow() != null ? s.getShow().getTitle() : null     // hent showTitle via show
                )
        );
    }
}
