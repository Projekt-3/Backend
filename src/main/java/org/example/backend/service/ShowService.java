package org.example.backend.service;

import jakarta.transaction.Transactional;
import org.example.backend.dto.ShowDTO;
import org.example.backend.model.Employee;
import org.example.backend.model.Show;
import org.example.backend.repository.IEmployeeRepository;
import org.example.backend.repository.IShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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


    public List<Show> getAllShows() {
        return iShowRepository.findAll();
    }

    @Transactional
    public Show addEmployeesToShow(Integer showId, List<Integer> employeeIds){

        Show show = iShowRepository.findById(showId).orElseThrow(() ->
                new RuntimeException("sho med id " + showId + "findes ikke"));

        List<Employee> employees = employeeRepository.findAllById(employeeIds);


        if (employees.isEmpty()) {
            throw new RuntimeException("Ingen gyldige medarbejdere fundet");
        }

        for (Employee emp : employees) {
            if (!show.getEmployees().contains(emp)) {
                show.getEmployees().add(emp);
            }
        }

        return iShowRepository.save(show);
    }

    public Optional<Show> getShow(Integer showId){
        return iShowRepository.findById(showId);
    }

}
