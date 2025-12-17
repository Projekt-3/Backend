package org.example.backend.service;

import org.example.backend.model.EmployeeShift;
import org.example.backend.repository.IEmpShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EmployeeShiftService {

    @Autowired
    private IEmpShiftRepository iEmpShiftRepository;

    /*
    public EmployeeShift getTodayShift (Integer empId){
        LocalDate today = LocalDate.now();

        return iEmpShiftRepository.findByEmployee_idAndShift_date(empId, today)
                .orElseThrow(() -> new RuntimeException("Ingen vagt i dag"));
    }

    public EmployeeShift checkIn (Integer empId, Integer shiftId){
        EmployeeShift employeeShift = iEmpShiftRepository.findByEmployee_idAndShift_id(empId, shiftId)
                        .orElseThrow(() -> new RuntimeException("Check in ikke muligt"));

        employeeShift.setCheckInStatus(true);
        return iEmpShiftRepository.save(employeeShift);
    }

    public EmployeeShift checkOut (Integer empId, Integer shiftId){
        EmployeeShift employeeShift = iEmpShiftRepository.findByEmployee_idAndShift_id(empId, shiftId)
                        .orElseThrow(()-> new RuntimeException("Check ud ikke muligt"));

        employeeShift.setCheckInStatus(false);
        return iEmpShiftRepository.save(employeeShift);
    }



     */
}
