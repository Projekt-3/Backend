package org.example.backend.repository;

import org.example.backend.model.EmployeeShift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmployeeShiftRepository extends JpaRepository<EmployeeShift, Integer> {
    List<EmployeeShift> findByEmployeeId(Integer employeeId);


}
