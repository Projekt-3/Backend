package org.example.backend.controller;

import org.example.backend.model.Shift;
import org.example.backend.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class ShiftController {

    @Autowired
    ShiftService shiftService;

    @PostMapping("/manager/register/shift")
    public ResponseEntity<String> createShift(@RequestBody Shift shift) {
        Shift created = shiftService.saveShift(shift);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Vagten er blevet oprettet med id: " + created.getId());
    }
}
