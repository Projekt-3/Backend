package org.example.backend.controller;

import org.example.backend.model.Show;
import org.example.backend.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class ShowController {

    @Autowired
    ShowService showService;

    @PostMapping("/api/shows")
    public ResponseEntity<Show> createShow(@RequestBody Show show) {
        Show created = showService.createShow(show);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
