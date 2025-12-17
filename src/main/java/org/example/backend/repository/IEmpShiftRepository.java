package org.example.backend.repository;

import org.example.backend.model.EmployeeShift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface IEmpShiftRepository extends JpaRepository<EmployeeShift, Integer> {

    Optional<EmployeeShift> findByEmployee_idAndShift_date(Integer empId, LocalDate startDate);

    Optional<EmployeeShift> findByEmployee_idAndShift_id(Integer empId, Integer shiftId);




}
