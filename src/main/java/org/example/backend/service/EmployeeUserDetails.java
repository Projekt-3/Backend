package org.example.backend.service;

import org.example.backend.model.Employee;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.Collections;

public class EmployeeUserDetails implements UserDetails {

    private final Employee employee;

    public EmployeeUserDetails(Employee employee) {
        this.employee = employee;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + employee.getRole().toString().toUpperCase())
        );
    }

    @Override
    public String getPassword() {
        return employee.getPassword(); // BCrypt-hash fra DB
    }

    @Override
    public String getUsername() {
        return employee.getMail(); // bruger email som username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // du kan tilføje felt i Employee hvis du vil håndtere det
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // tilføj evt. felt i Employee
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // tilføj evt. felt i Employee
    }

    @Override
    public boolean isEnabled() {
        return true; // tilføj evt. felt i Employee
    }

    // Tilgang til Employee-objektet hvis nødvendigt
    public Employee getEmployee() {
        return employee;
    }
}
