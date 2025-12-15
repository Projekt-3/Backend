package org.example.backend.service;

import org.example.backend.dto.ShiftDTO;
import org.example.backend.model.Shift;
import org.example.backend.model.Show;
import org.example.backend.repository.IShiftRepository;
import org.example.backend.repository.IShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShiftService {

    @Autowired
    IShiftRepository iShiftRepository;

    public Shift saveShift(Shift shift) {
        return iShiftRepository.save(shift);
    }

    public List<ShiftDTO> getAllShifts(){
        return iShiftRepository.findAll()
                .stream()
                .map(shift -> new ShiftDTO(
                        shift.getId(),
                        shift.getPlannedStart(),
                        shift.getPlannedEnd(),
                        shift.getShow() != null ? shift.getShow().getId() : null,
                        shift.getShow() != null ? shift.getShow().getTitle() : null
                ))
                .toList();
    }
}
