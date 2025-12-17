package org.example.backend.service;

import org.example.backend.model.Employee;
import org.example.backend.model.EmployeeShift;
import org.example.backend.model.Shift;
import org.example.backend.repository.IEmployeeRepository;
import org.example.backend.repository.IEmployeeShiftRepository;
import org.example.backend.repository.IShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeShiftService {

    @Autowired
    IEmployeeShiftRepository iEmployeeShiftRepository;

    @Autowired
    IEmployeeRepository iEmployeeRepository;

    @Autowired
    IShiftRepository iShiftRepository;

    public EmployeeShift addEmployeeToShift(Integer shiftId, Integer employeeId) {
        Shift shift = iShiftRepository.findById(shiftId)
                .orElseThrow(() -> new RuntimeException("Shift ikke fundet med id: " + shiftId));

        Employee employee = iEmployeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee ikke fundet med id: " + employeeId));

        Optional<EmployeeShift> existing = iEmployeeShiftRepository
                .findAll()
                .stream()
                .filter(es -> es.getEmployee().getId().equals(employeeId)
                        && es.getShift().getId().equals(shiftId))
                .findFirst();

        if (existing.isPresent()) {
            return existing.get();
        }

        EmployeeShift employeeShift = EmployeeShift.builder()
                .employee(employee)
                .shift(shift)
                .checkInStatus(false)
                .build();

        return iEmployeeShiftRepository.save(employeeShift);
    }



}
