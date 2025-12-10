package org.example.backend.service;

import org.example.backend.model.Show;
import org.example.backend.repository.IShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowService {

    @Autowired
    IShowRepository iShowRepository;

    public Show saveShow(Show show){
        return iShowRepository.save(show);
    }

}
