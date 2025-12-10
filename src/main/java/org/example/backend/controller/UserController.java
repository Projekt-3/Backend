package org.example.backend.controller;

import org.example.backend.model.Employee;
import org.example.backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dashboard")
public class UserController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/manager/register/employee")
    public ResponseEntity<String> createUser(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Brugeren er oprettet med id: " + savedEmployee.getId());
    }


    // -------- READ EMP ----------

    @GetMapping("/manager/employees")
    public List<Employee> getAllEmp (){
        return employeeService.getAllEmp();
    }

    @GetMapping ("/manager/employees/{id}")
    public ResponseEntity<Employee> getEmpById(@PathVariable int id){
        Optional<Employee> employee = employeeService.getEmpById(id);

        if(employee.isPresent()){
            return ResponseEntity.ok(employee.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ------ UPDATE EMP ---------

    @PutMapping("/manager/employees/{id}")
    public ResponseEntity<String> updateEmp(@PathVariable int id, @RequestBody Employee updatedEmp){
    boolean updated =  employeeService.updateEmpById(id, updatedEmp);

    if(!updated){
        return ResponseEntity.status(404).body("Medarbejder ikke fundet");
    }

    return ResponseEntity.ok("Medarbejderens informationer er opdateret");
    }

    // ------ DELETE EMP ----------
    @DeleteMapping ("/manager/employees/{id}")
    public ResponseEntity<String> deleteEmp (@PathVariable int id){
        Optional<Employee> employee = employeeService.getEmpById(id);

        if(employee.isPresent()){
            employeeService.deleteEmpById(id);
            return ResponseEntity.ok("Medarbejder er slettet");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medarbejder ikke fundet");
        }
    }



}
