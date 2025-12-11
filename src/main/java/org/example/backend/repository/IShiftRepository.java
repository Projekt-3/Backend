package org.example.backend.repository;


import org.example.backend.model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IShiftRepository extends JpaRepository<Shift, Integer> {
}
