package org.example.backend.service;
import org.example.backend.dto.ShiftDTO;
import org.example.backend.model.Shift;
import org.example.backend.model.Show;
import org.example.backend.repository.IShiftRepository;
import org.example.backend.repository.IShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ShiftService {

    @Autowired
    IShiftRepository iShiftRepository;

    @Autowired
    IShowRepository iShowRepository;

    public Shift saveShift(Shift shift) {
        return iShiftRepository.save(shift);
    }

    public List<ShiftDTO> getAllShifts(){
        return iShiftRepository.findAll()
                .stream()
                .map(shift -> new ShiftDTO(
                        shift.getId(),
                        shift.getDate(),
                        shift.getPlannedStart(),
                        shift.getPlannedEnd(),
                        shift.getShow() != null ? shift.getShow().getId() : null,
                        shift.getShow() != null ? shift.getShow().getTitle() : null
                ))
                .toList();
    }

    public Optional<Shift> getShiftbyId(int id) {
        return iShiftRepository.findById(id);
    }

    public ShiftDTO updateShift(Integer id, ShiftDTO dto) {
        Shift shift = iShiftRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shift not found"));
        shift.setPlannedStart(dto.plannedStart());
        shift.setPlannedEnd(dto.plannedEnd());

        if (dto.showId() != null) {
            Show show = iShowRepository.findById(dto.showId())
                    .orElseThrow(() -> new RuntimeException("Show no found"));
            shift.setShow(show);
        } else {
            shift.setShow(null);
        }

        Shift saved = iShiftRepository.save(shift);
        return new ShiftDTO(
                saved.getId(),
                saved.getPlannedStart(),
                saved.getPlannedEnd(),
                saved.getShow() != null ? saved.getShow().getId() : null,
                saved.getShow() != null ? saved.getShow().getTitle() : null
        );
    }

    public void deleteShift(int id) {
        iShiftRepository.deleteById(id);
    }
}

