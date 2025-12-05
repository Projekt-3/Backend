package org.example.backend.controller;

import org.example.backend.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/dologin")
    public ResponseEntity<Map<String, String>> doLogin(@RequestBody Employee employee) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(employee.getMail(), employee.getPassword()));
        if (authentication.isAuthenticated()) {
            String token = "GENERATED_JWT"; // fra JWTTokenGeneratorFilter
            Map<String, String> body = new HashMap<>();
            body.put("token", token);
            return ResponseEntity.ok(body);
        } else {
            throw new UsernameNotFoundException("Invalid user request..!!");
        }
    }
}
