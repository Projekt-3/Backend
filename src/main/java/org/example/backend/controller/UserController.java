package org.example.backend.controller;

import org.example.backend.dto.EmployeeDTO;
import org.example.backend.model.Employee;
import org.example.backend.repository.IEmployeeRepository;
import org.example.backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class UserController {

    @Autowired
    EmployeeService employeeService;


    // Til at registrere nye users
    @PostMapping("/manager/register")
    public ResponseEntity<String> createUser(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Brugeren er oprettet med id: " + savedEmployee.getId());
    }

    @GetMapping("/api/employees")
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
}
