package org.example.backend.controller;

import org.example.backend.model.Employee;
import org.example.backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
