package org.example.backend.repository;

import org.example.backend.model.EmployeeShift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployeeShiftRepository extends JpaRepository<EmployeeShift, Integer> {



}
