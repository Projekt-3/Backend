package org.example.backend.controller;
import org.example.backend.model.Employee;
import org.example.backend.constants.SecurityConstants;
import org.example.backend.repository.IEmployeeRepository;
import org.example.backend.service.EmployeeLoginService;
import org.example.backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.*;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/dologin")
    public ResponseEntity<Map<String, Object>> doLogin(@RequestBody Employee employee) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(employee.getMail(), employee.getPassword())
        );
        System.out.println(authentication);

        if (authentication.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generer JWT
            SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));
            String jwt = Jwts.builder()
                    .setIssuer("Eazy Bank")
                    .setSubject("JWT Token")
                    .claim("username", authentication.getName())
                    .claim("authorities", populateAuthorities(authentication.getAuthorities()))
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 30_000_000)) // token varighed
                    .signWith(key)
                    .compact();

            Employee loggedInEmp = employeeService.findEmployeeByMail(authentication.getName());

            Map<String, Object> body = new HashMap<>();
            body.put("token", jwt);
            body.put("employee", loggedInEmp);

            return ResponseEntity.ok(body);
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        HashSet<String> set = new HashSet<>();
        for (GrantedAuthority auth : authorities) {
            set.add(auth.getAuthority());
        }
        return String.join(",", set);
    }
}
