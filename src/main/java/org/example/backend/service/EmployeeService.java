package org.example.backend.service;

import org.example.backend.model.Employee;
import org.example.backend.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    private void validatePassword (String password) {
        if (password.length() < 8) {
            throw new IllegalArgumentException ("Password skal indeholde mindst 8 tegn");
        }
        if (!password.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException("Password skal indeholde mindst ét stort bogstav");
        }
        if (!password.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Password skal indeholde mindst ét tal");
        }
    }

    private void validateEmailUnique (String mail) {
        if (iEmployeeRepository.findByMail(mail).isPresent()) {
            throw new IllegalArgumentException("Email is already registered");
        }
    }

    public List<Employee> getAllEmp(){
        return iEmployeeRepository.findAll();
    }

    public Optional<Employee> getEmpById(int id){
        return iEmployeeRepository.findById(id);
    }

    public Boolean updateEmpById(int id, Employee updatedEmp){
        Optional<Employee> employee = iEmployeeRepository.findById(id);

        if(employee.isEmpty()){
            return false;
        }

        Employee existingEmp = employee.get();

        existingEmp.setFirstname(updatedEmp.getFirstname());
        existingEmp.setLastname(updatedEmp.getLastname());
        existingEmp.setUsername(updatedEmp.getUsername());
        existingEmp.setRole(updatedEmp.getRole());
        existingEmp.setMail(updatedEmp.getMail());
        existingEmp.setPhone(updatedEmp.getPhone());

        iEmployeeRepository.save(existingEmp);
        return true;
    }

    public void deleteEmpById(int id){
        iEmployeeRepository.deleteById(id);
    }

}
