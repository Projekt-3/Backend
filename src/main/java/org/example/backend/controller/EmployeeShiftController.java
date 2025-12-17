package org.example.backend.controller;

import org.example.backend.model.EmployeeShift;
import org.example.backend.service.EmployeeShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
