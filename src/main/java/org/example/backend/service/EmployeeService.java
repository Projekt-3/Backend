package org.example.backend.service;

import org.example.backend.dto.EmployeeDTO;
import org.example.backend.model.Employee;
import org.example.backend.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class EmployeeService {

    @Autowired
    IEmployeeRepository iEmployeeRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Employee createEmployee(Employee employee) {
        validatePassword(employee.getPassword());
        validateEmailUnique(employee.getMail());

        employee.setPassword(passwordEncoder.encode(employee.getPassword()));

        return iEmployeeRepository.save(employee);
    }

    private void validatePassword(String password) {
        if (password.length() < 8) {
            throw new IllegalArgumentException("Password skal indeholde mindst 8 tegn");
        }
        if (!password.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException("Password skal indeholde mindst ét stort bogstav");
        }
        if (!password.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Password skal indeholde mindst ét tal");
        }
    }

    private void validateEmailUnique(String mail) {
        if (iEmployeeRepository.findByMail(mail).isPresent()) {
            throw new IllegalArgumentException("Email is already registered");
        }
    }

    public List<EmployeeDTO> getAllEmployees() {
        return iEmployeeRepository.findAll()
                .stream().map(emp ->
                    new EmployeeDTO(emp.getId(), emp.getFirstname(), emp.getLastname()))
            .toList();
    }

}
