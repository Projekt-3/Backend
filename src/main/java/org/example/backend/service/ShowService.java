package org.example.backend.service;

import org.example.backend.dto.ShowDTO;
import org.example.backend.model.Employee;
import org.example.backend.model.Show;
import org.example.backend.repository.IEmployeeRepository;
import org.example.backend.repository.IShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    IShowRepository iShowRepository;


    @Autowired
    private IEmployeeRepository employeeRepository;



    public Show createShow(ShowDTO dto) {

        List<Employee> employees = employeeRepository.findAllById(dto.getEmployees());


        Show show = Show.builder()
                .title(dto.getName())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .employees(employees)
                .build();

        return iShowRepository.save(show);
    }

}
