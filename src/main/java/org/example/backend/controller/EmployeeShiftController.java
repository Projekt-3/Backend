package org.example.backend.controller;

import org.example.backend.dto.EmployeeShiftDTO;
import org.example.backend.model.EmployeeShift;
import org.example.backend.service.EmployeeShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class EmployeeShiftController {

    @Autowired
    EmployeeShiftService employeeShiftService;

    @PostMapping("/shift/{shiftId}/employee/{employeeId}")
    public ResponseEntity<EmployeeShift> addEmployeeToShift(
            @PathVariable Integer shiftId,
            @PathVariable Integer employeeId) {

        EmployeeShift employeeShift = employeeShiftService.addEmployeeToShift(shiftId, employeeId);
        return ResponseEntity.ok(employeeShift);
    }

    // Hent alle vagter for en medarbejder
    @GetMapping("/employee/{employeeId}/shifts")
    public ResponseEntity<List<EmployeeShiftDTO>> getShiftsForEmployee(@PathVariable Integer employeeId) {
        List<EmployeeShiftDTO> dtos = employeeShiftService.getShiftsForEmployeeDTO(employeeId);
        return ResponseEntity.ok(dtos);
    }

    // PATCH/PUT til at checke ind/ud
    @PatchMapping("/employee-shift/{employeeShiftId}/checkin")
    public ResponseEntity<EmployeeShift> checkInOrOut(
            @PathVariable Integer employeeShiftId,
            @RequestParam boolean status) {

        EmployeeShift updated = employeeShiftService.updateCheckInStatus(employeeShiftId, status);
        return ResponseEntity.ok(updated);
    }


}
