package org.example.backend.service;

import org.example.backend.model.Show;
import org.example.backend.repository.IShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowService {

    @Autowired
    IShowRepository showRepository;

    public ShowService(IShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    public Show createShow(Show show) {
        return showRepository.save(show);
    }
}
