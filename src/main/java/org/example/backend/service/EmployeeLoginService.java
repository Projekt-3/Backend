package org.example.backend.service;

import org.example.backend.model.Employee;
import org.example.backend.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EmployeeLoginService implements UserDetailsService {

    @Autowired
    IEmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Employee employee = employeeRepository.findByMail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return new EmployeeUserDetails(employee);
    }
}
