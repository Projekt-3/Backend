package org.example.backend.controller;

import org.example.backend.dto.ShowDTO;
import org.example.backend.model.Show;
import org.example.backend.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class ShowController {

    @Autowired
    ShowService showService;

    @PostMapping("/manager/register/show")
    public ResponseEntity<String> createShow (@RequestBody ShowDTO dto){
       Show created = showService.createShow(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Forestillingen er oprettet med id: " + created.getId());
    }


    @GetMapping("/manager/shows")
    public List<Show> getAllShows(){
        return showService.getAllShows();
    }


}
