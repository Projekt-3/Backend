package org.example.backend.controller;

import org.example.backend.model.Employee;
import org.example.backend.model.Show;
import org.example.backend.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class ShowController {

    @Autowired
    ShowService showService;

    @PostMapping("/manager/shows")
    public ResponseEntity<String> createShow (@RequestBody Show show){
       Show created = showService.saveShow(show);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Forestillingen er oprettet med id: " + created.getId());
    }



}
