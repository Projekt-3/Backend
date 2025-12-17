package org.example.backend.controller;

import org.example.backend.model.EmployeeShift;
import org.example.backend.service.EmployeeShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard")
public class EmpShiftController {

    @Autowired
    private EmployeeShiftService employeeShiftService;

    @GetMapping("/employee/{empId}/shift")
    public ResponseEntity<EmployeeShift> getTodayShift(@PathVariable Integer empId){
        try{
            EmployeeShift shift = employeeShiftService.getTodayShift(empId);
            return ResponseEntity.ok(shift);
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/employee/{empId}/shift/{shiftId}/check-in")
    public ResponseEntity<EmployeeShift> checkIn (@PathVariable Integer empId, @PathVariable Integer shiftId){
        try{
            return ResponseEntity.ok(employeeShiftService.checkIn(empId, shiftId));
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/employee/{empId}/shift/{shiftId}/check-out")
    public ResponseEntity<EmployeeShift> checkOut (@PathVariable Integer empId, @PathVariable Integer shiftId){
        try{
            return ResponseEntity.ok(employeeShiftService.checkOut(empId, shiftId));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().build();
        }
    }

}
