package org.example.backend.service;

import org.example.backend.dto.EmployeeShiftDTO;
import org.example.backend.model.Employee;
import org.example.backend.model.EmployeeShift;
import org.example.backend.model.Shift;
import org.example.backend.repository.IEmployeeRepository;
import org.example.backend.repository.IEmployeeShiftRepository;
import org.example.backend.repository.IShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<EmployeeShift> getShiftsForEmployee(Integer employeeId) {
        return iEmployeeShiftRepository.findByEmployeeId(employeeId);
    }


    public List<EmployeeShiftDTO> getShiftsForEmployeeDTO(Integer employeeId) {
        return iEmployeeShiftRepository.findByEmployeeId(employeeId)
                .stream()
                .map(EmployeeShiftDTO::fromEntity)
                .collect(Collectors.toList());
    }


    public EmployeeShift updateCheckInStatus(Integer employeeShiftId, boolean status) {
        EmployeeShift es = iEmployeeShiftRepository.findById(employeeShiftId)
                .orElseThrow(() -> new RuntimeException("EmployeeShift ikke fundet med id: " + employeeShiftId));
        es.setCheckInStatus(status);
        return iEmployeeShiftRepository.save(es);
    }


}
