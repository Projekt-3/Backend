package org.example.backend.controller;

import org.example.backend.model.Employee;
import org.example.backend.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/dashboard")
public class UserController {

    @Autowired
    IEmployeeRepository iEmployeeRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    // Til at registrere nye users
    @PostMapping("/manager/register")
    public ResponseEntity<String> registerUser(@RequestBody Employee employee) {
        Employee savedEmployee = null;
        ResponseEntity response = null;
        try {
            String hashPwd = passwordEncoder.encode(employee.getPassword());
            employee.setPassword(hashPwd);
            savedEmployee = iEmployeeRepository.save(employee);
            if (savedEmployee.getId() > 0) {
                response = ResponseEntity.status(HttpStatus.CREATED)
                        .body("Given user details are successfully registrered");
            }
        } catch (Exception ex) {
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception due to" + ex.getMessage());
        }
        return response;
    }
}
