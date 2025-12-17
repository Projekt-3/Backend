package org.example.backend.controller;

import org.example.backend.dto.ShiftDTO;
import org.example.backend.model.Shift;
import org.example.backend.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/manager/shifts")
    public List<ShiftDTO> getAllShifts() {
        return shiftService.getAllShifts();
    }

    @GetMapping("/manager/shift/{id}")
    public ResponseEntity<Shift> getShiftById(@PathVariable int id) {
        Optional<Shift> shift = shiftService.getShiftbyId(id);

        if (shift.isPresent()) {
            return ResponseEntity.ok(shift.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/manager/shift/{id}")
    public ResponseEntity<ShiftDTO> updateShift(@PathVariable Integer id, @RequestBody ShiftDTO dto) {
        try {
            ShiftDTO updated = shiftService.updateShift(id, dto);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/manager/shift/{id}")
    public ResponseEntity<String> deleteShift(@PathVariable int id) {
        Optional<Shift> shift = shiftService.getShiftbyId(id);

        if (shift.isPresent()) {
            shiftService.deleteShift(id);
            return ResponseEntity.ok("Vagten er slettet");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vagten findes ikke");
        }
    }
}
