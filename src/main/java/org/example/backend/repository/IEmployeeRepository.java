package org.example.backend.repository;


import org.example.backend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByMail(String mail);
}
